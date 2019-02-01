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


import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.*;

import spock.lang.Specification;
import springData.domain.OrganizerUser
import springData.repository.RoleRepository
import springData.repository.UserRepository


/**
 * This specification checks Task 2 part 1 and 2 in the original project of the students.
 *
 */
@SpringBootTest(classes=[OrganizerApp.class,WebConfig.class])
public class Task2_12 extends Specification {

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
						.build();
	}
	
	// Task2.1
	// next 3 are together worth 4p (added repository)
	def "ADMIN role" () { //1p
		expect:
			assertThat(roleRepo.findById(0), Matchers.hasProperty('role', Matchers.is('ADMIN')));
	}
	def "MANAGER role" () { //1p
		expect:
			assertThat(roleRepo.findById(1), Matchers.hasProperty('role', Matchers.is('MANAGER')));
	}
	def "ASSISTANT role" () { //1p
		expect:
			assertThat(roleRepo.findById(2), Matchers.hasProperty('role', Matchers.is('ASSISTANT')));
	}
	
	// Task2.2
	def "admin user" () { //1p
		expect:
			assertThat(userRepo.findByLogin('admin'), Matchers.hasProperty('role', Matchers.hasProperty('role', Matchers.is('ADMIN'))));
	}	
	def "admin password" () { //1p
		given:
			BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
		expect:
			assertThat('wrong password', pe.matches('admin', userRepo.findByLogin('admin').getPassword()));
	}
	
}
