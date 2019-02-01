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

import static org.hamcrest.MatcherAssert.*;

import spock.lang.Shared
import spock.lang.Specification;
import spock.lang.Unroll
import springData.controller.AuthenticationController
import springData.domain.Organizer
import springData.domain.OrganizerUser
import springData.domain.Todo
import springData.repository.OrganizerRepository
import springData.repository.RoleRepository
import springData.repository.TodoRepository
import springData.repository.UserRepository
import springData.services.OrganizerUserDetailsService

/**
 * This specification checks Task 2 parts 3, 4, and 5.<br/><br/>
 * 
 * To execute correctly, this test needs
 * <ul>
 *  <li>the updated JPA annotations of classes OrganizerUser and Role</li>
 *  <li>the role needs to be stored ADMIN explicitly in OrganizerApp</li>
 * </ul>
 */
@SpringBootTest(classes=[OrganizerApp.class,SecurityConfig.class,WebConfig.class,OrganizerUserDetailsService.class,AuthenticationController.class,])
public class Task2 extends Specification {

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
	private int id = -1;

	def setup() {
		this.mockMvc = MockMvcBuilders
						.webAppContextSetup(this.wac)
						.apply(springSecurity())
						.build();
						
		if  (first) {
			OrganizerUser m1 = new OrganizerUser();
			m1.setLogin('manager')
			m1.setPassword('XXX')
	
			m1.setRole(roleRepo.findByRole('MANAGER'))
			
			Organizer o1 = new Organizer();
				o1.addTodo(new Todo('t1mTask2', 'd1mTask2'))
				o1.setOwner(m1)
			m1.getOrganizers().add(o1)			
			userRepo.save(m1);
			this.id = todoRepo.findAll().getAt(0).getId();
			this.first = false;
		}

	}

	// Task2.3
	@Unroll
	def "authentication #password"() { //2p
		given:
			ResultActions result = this.mockMvc.perform(formLogin('https://localhost/login').user(user).password(password))
		expect:
			if (valid) {
				result.andDo(print())
				result.andExpect(redirectedUrl('/success-login'))
				result.andExpect(authenticated())
			} else {
				result.andExpect(redirectedUrl('/error-login'))
				result.andExpect(unauthenticated())
			}
		where:
			user | password | valid
			'admin' | 'admin' | true
			'admin' | 'bdmin' | false
			'ccccc' | 'cdmin' | false
	}
	
	// Task2.4
	@Unroll
	def "authorization-#testId"() { // 4p in total
		when: "I perform a get #request"
			result = this.mockMvc.perform(get("$requestUrl").param('id', '' + this.id).secure( true ).with(user('manager').roles(role)))
		then:
			result.andExpect(status().is(statusCode))
		where:
			testId | method | requestUrl |  role | statusCode
			1 | 'get' | '/admin/create' |  "ADMIN" | 200
			2 | 'get' | '/admin/create' |  "MANAGER" | 403
			3 | 'get' | '/create/' |  "MANAGER" | 200
			4 | 'get' | '/create/' |  "ADMIN" | 403
			5 | 'get' | '/delete/' | "MANAGER" | 302
			6 | 'get' | '/delete/' |  "ASSISTANT" | 403
			7 | 'get' | '/list/' | "MANAGER" | 200
			8 | 'get' | '/list/' | "ASSISTANT" | 200
			9 | 'get' | '/list/' | "ADMIN" | 403
	}

	// Task2.5
	@Unroll
	def "successLogin #role"() { //2p
		given:
			if (userRepo.findByLogin('manager') == null) {
				OrganizerUser m = new OrganizerUser();
				m.setLogin('manager');
				m.setPassword('XXX');
				m.setRole(roleRepo.findByRole('MANAGER'));
				userRepo.save(m)
			}
		and:
			ResultActions result = this.mockMvc.perform(get('/success-login').secure(true).with(user(user).roles(role)))
		expect:
			result.andExpect(redirectedUrl(redirect))
		where:
			user | role | redirect
			'admin' | 'ADMIN' | '/admin/create'
			'manager' | 'MANAGER' | '/list'
	}
}
