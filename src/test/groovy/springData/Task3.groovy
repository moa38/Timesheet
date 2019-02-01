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

import static org.hamcrest.MatcherAssert.*;

import spock.lang.Specification;
import spock.lang.Unroll
import springData.controller.AdminController
import springData.controller.OrganizerUserValidator
import springData.domain.OrganizerUser
import springData.domain.Role
import springData.repository.OrganizerRepository
import springData.repository.RoleRepository
import springData.repository.TodoRepository
import springData.repository.UserRepository
import springData.services.OrganizerUserDetailsService

/**
 * This specification checks Task 3.<br/><br/>
 *
 * To execute correctly, this test needs
 * <ul>
 *  <li>the updated JPA annotations of classes OrganizerUser and Role</li>
 *  <li>the role needs to be stored ADMIN explicitly in OrganizerApp</li>
 * </ul>
 */
@SpringBootTest(classes=[OrganizerApp.class,SecurityConfig.class,WebConfig.class,OrganizerUserDetailsService.class,AdminController.class,OrganizerUserValidator.class])
public class Task3 extends Specification {

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
	
	def setup() {
		this.mockMvc = MockMvcBuilders
						.webAppContextSetup(this.wac)
						.apply(springSecurity())
						.build()
	}
	
	def "empty login"() { //2p
		given:
			ResultActions result = this.mockMvc.perform(post('/admin/create').secure(true)
					.with(csrf())
					.param('login', '')
					.param('password', 'xxx')
					.param('password2', 'xxx')
					.param('roleName', 'ASSISTANT')
					.param('add', '')
				.with(user('admin').roles('ADMIN'))
				)
		expect:
			result.andExpect(status().isOk())
			result.andExpect(view().name('admin/CreateUser'))
			result.andExpect(model().attributeHasErrors('orgUser'))
	}
	
	def "existing user"() { //3p
		given:
			if (userRepo.findByLogin('xxx') == null) {
				OrganizerUser m = new OrganizerUser();
				m.setLogin('xxx');
				m.setPassword('XXX');
				m.setRole(roleRepo.findByRole('MANAGER'));
				userRepo.save(m)
			}
		and:
			ResultActions result = this.mockMvc.perform(post('/admin/create').secure(true)
				.with(csrf())
				.with(user('admin').roles('ADMIN'))
				.param('login', 'xxx')
				.param('password', 'xxx')
				.param('password2', 'xxx')
				.param('roleName', 'ASSISTANT')
				.param('add', '')
				)
		expect:
			result.andExpect(status().isOk())
			result.andExpect(view().name('admin/CreateUser'))
			result.andExpect(model().attributeHasErrors('orgUser'))
	}
	
	def "new user"() { //3p
		given:
			ResultActions result = this.mockMvc.perform(post('/admin/create').secure(true)
				.with(csrf()).with(user('admin').roles('ADMIN'))
				.param('login', 'new')
				.param('password', 'xxx')
				.param('password2', 'xxx')
				.param('roleName', 'ASSISTANT')
				.param('add', '')
				)
		expect:
			result.andExpect(status().isOk())
			result.andExpect(view().name('admin/done'))
		and:
			assertThat('not stored', userRepo.findByLogin('new') != null)
	}
	
	def "password hashed"() { //2p
		given:
			ResultActions result = this.mockMvc.perform(post('/admin/create').secure(true)
				.with(csrf()).with(user('admin').roles('ADMIN'))
				.param('login', 'again')
				.param('password', 'password')
				.param('password2', 'password')
				.param('roleName', 'ASSISTANT')
				.param('add', '')
				)
		expect:
			result.andExpect(status().isOk())
			result.andExpect(view().name('admin/done'))
		and:
			BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
			assertThat('not encrypted', pe.matches('password', userRepo.findByLogin('again').getPassword()))
	}
}
