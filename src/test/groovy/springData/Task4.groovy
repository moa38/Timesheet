package springData;

import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

import javax.management.relation.RoleResult
import javax.transaction.Transactional

import static org.hamcrest.MatcherAssert.*;

import spock.lang.Shared
import spock.lang.Specification;
import spock.lang.Stepwise
import spock.lang.Unroll
import springData.controller.DisplayTodoController
import springData.controller.OrganizerUserValidator
import springData.domain.Organizer
import springData.domain.OrganizerUser
import springData.domain.Role
import springData.domain.Todo
import springData.repository.OrganizerRepository
import springData.repository.RoleRepository
import springData.repository.TodoRepository
import springData.repository.UserRepository
import springData.services.OrganizerUserDetailsService

/**
 * This specification checks Task 4.<br/><br/>
 *
 * To execute correctly, this test needs
 * <ul>
 *  <li>the updated JPA annotations of classes OrganizerUser and Role</li>
 *  <li>the role needs to be stored ADMIN explicitly in OrganizerApp</li>
 * </ul>
 */
@SpringBootTest(classes=[OrganizerApp.class,SecurityConfig.class,WebConfig.class,OrganizerUserDetailsService.class,DisplayTodoController.class])
public class Task4 extends Specification {

	@Autowired
	TodoRepository todoRepo;

	@Autowired
	OrganizerRepository organizerRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	private ResultActions result;
	
	@Shared
	private boolean first = true;
	@Shared
	private int t1gone = 0;
	
	def setup() {
		this.mockMvc = MockMvcBuilders
						.webAppContextSetup(this.wac)
						.apply(springSecurity())
						.build();
						
		if  (first) {			
			OrganizerUser m1 = new OrganizerUser();
			m1.setLogin('m1')
			m1.setPassword('passwordM1')
	
			m1.setRole(roleRepo.findByRole('MANAGER'))
			
			Organizer o1 = new Organizer();
				o1.addTodo(new Todo('t1', 'd1'))
				o1.getTodos().get(0).setPriority(5)
				o1.addTodo(new Todo('t2', 'd2'))
				o1.getTodos().get(1).setPriority(5)
				o1.setOwner(m1)
			m1.getOrganizers().add(o1)
			
			Organizer o2 = new Organizer();
				o2.addTodo(new Todo('t3', 'd3'))
				o2.getTodos().get(0).setPriority(23)
				o2.setOwner(m1)
			m1.getOrganizers().add(o2)
			userRepo.save(m1);
			
			OrganizerUser m2 = new OrganizerUser();
			m2.setLogin('m2')
			m2.setPassword('passwordM2')
			m2.setRole(roleRepo.findByRole('MANAGER'))
			Organizer o3 = new Organizer();
				o3.addTodo(new Todo('t4', 'd4'))
				o3.setOwner(m2)
			m2.getOrganizers().add(o3)
			userRepo.save(m2);
			
			OrganizerUser m3 = new OrganizerUser();
			m3.setLogin('m3')
			m3.setPassword('passwordM3')
			m3.setRole(roleRepo.findByRole('MANAGER'))
			userRepo.save(m3);
			
			OrganizerUser a = new OrganizerUser();
			a.setLogin('assistant')
			a.setPassword('passwordAssistant')
			a.setRole(roleRepo.findByRole('ASSISTANT'))
			userRepo.save(a);
			first = false;
		}

	}
	
	// Task4.2
	def "list for assistant"() { //2p
		given:
			ResultActions result = this.mockMvc.perform(get('/list').secure(true)
				.with(user('assistant').roles('ASSISTANT'))
				)
		expect:
			result.andExpect(status().isOk())
			result.andExpect(view().name('ListTodos'))
			result.andExpect(model().attribute('todos', Matchers.hasSize(4 - t1gone)))
	}
	
	def "list for m1"() { //2p
		given:
			ResultActions result = this.mockMvc.perform(get('/list').secure(true)
				.with(user('m1').roles('MANAGER'))
				)
		expect:
			result.andExpect(status().isOk())
			result.andExpect(view().name('ListTodos'))
			result.andExpect(model().attribute('todos', Matchers.hasSize(3 - t1gone)))
	}

	def "list for m3"() { //2p
		given:
			ResultActions result = this.mockMvc.perform(get('/list').secure(true)
				.with(user('m3').roles('MANAGER'))
				)
		expect:
			result.andExpect(status().isOk())
			result.andExpect(view().name('NoTodo'))
	}
	
	// Task4.3
	def "next for assistant"() { //2p
		given:
			ResultActions result = this.mockMvc.perform(get('/next').secure(true)
				.with(user('assistant').roles('ASSISTANT'))
				)
		expect:
			result.andExpect(status().isOk())
			result.andExpect(view().name('NextTodo'))
			result.andExpect(model().attribute('todo', Matchers.hasProperty('task', Matchers.is('t3'))))
	}
	
	def "next for m2"() { //2p
		given:
			ResultActions result = this.mockMvc.perform(get('/next').secure(true)
				.with(user('m2').roles('MANAGER'))
				)
		expect:
			result.andExpect(status().isOk())
			result.andExpect(view().name('NextTodo'))
			result.andExpect(model().attribute('todo', Matchers.hasProperty('task', Matchers.is('t4'))))
	}

	def "next for m3"() { //2p
		given:
			ResultActions result = this.mockMvc.perform(get('/next').secure(true)
				.with(user('m3').roles('MANAGER'))
				)
		expect:
			result.andExpect(status().isOk())
			result.andExpect(view().name('NoTodo'))
	}
	
	// Task4.4
	def "create for m1 exists"() { //2p
		given:
			ResultActions result = this.mockMvc.perform(post('/create').secure(true)
				.with(csrf()).with(user('m1').roles('MANAGER'))
				.param('task', 'tNm1')
				.param('description', 'dNm1')
				.param('priority', '0')
				.param('important', '0')
				.param('add', '')
				)
		expect:
			result.andExpect(redirectedUrl('/list'))
		and:
			assertThat('todo exists', todoRepo.findByTask('tNm1').size() == 1)
	}
	
	@Transactional
	def "create for m1 in first organizer"() { //2p
		expect:
			Todo t = todoRepo.findByTask('tNm1').get(0)
			assertThat('todo exists', t != null)
			List<Organizer> os = userRepo.findByLogin('m1').getOrganizers();
			assertThat('todo in organizer', os.get(0).getTodos().contains(t))
	}
	
	@Transactional
	def "create for m3"() { //2p
		given:
			ResultActions result = this.mockMvc.perform(post('/create').secure(true)
				.with(csrf()).with(user('m3').roles('MANAGER'))
				.param('task', 'tNm3')
				.param('description', 'dNm3')
				.param('priority', '0')
				.param('important', '0')
				.param('add', '')
				)
		expect:
			result.andExpect(redirectedUrl('/list'))
		and:
			assertThat('todo exists', todoRepo.findByTask('tNm3').size() == 1)
			Todo t = todoRepo.findByTask('tNm3').get(0)
			assertThat('todo in organizer', userRepo.findByLogin('m3').getOrganizers().get(0).getTodos().contains(t))
	}
	
	// Task4.5
	@Transactional
	def "delete for m1"() { //6p
		given:
			Todo t = todoRepo.findByTask('t1').get(0); 	
			int id = t.getId()			
			ResultActions result = this.mockMvc.perform(get('/delete')
				.param('id', '' + id)
				.secure(true)
				.with(user('m1').roles('MANAGER'))
				)
			t1gone = 1;
		expect:
			result.andExpect(redirectedUrl('/list'))
		and:
			assertThat('todo removed from organizer', !userRepo.findByLogin('m1').getOrganizers().get(0).getTodos().contains(t))
		and:
			assertThat('todo is gone', todoRepo.findById(id) == null || 
				(todoRepo.findById(id) instanceof Optional && !((Optional)todoRepo.findById(id)).isPresent()))
	}
}
