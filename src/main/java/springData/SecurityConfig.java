package springData;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests().antMatchers("/", "/index").permitAll().anyRequest().authenticated().and().formLogin()
            .loginPage("/login").defaultSuccessUrl("/add-shift", true).permitAll().and().logout().permitAll();
   }

   @Bean
   @Override
   public UserDetailsService userDetailsService() {
      UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();

      return new InMemoryUserDetailsManager(user);
   }
}

//@EnableWebSecurity
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    protected void configure(HttpSecurity http) throws Exception {
//    	http
//        // SECURE COMMUNICATION
//    		// TODO: exercise 6 Task 1c
//	    .requiresChannel()
//	    		.anyRequest()
//	    		.requiresSecure()
//	    	// END exercise 6 Task 1c
//   		// AUTHENTICATION
//	    	.and()
//		    .formLogin()
//		    		// to show the page where we enter login credentials 
//				.loginPage("/login") 
//				// to process authentication: /login handler method implemented by Spring Security
//				.loginProcessingUrl("/login")
//				// where to go after successful login
//				.defaultSuccessUrl("/add-shift",true) // the second parameter is for enforcing this url always
//				// to show an error page if the authentication failed
//				.failureUrl("/error-login")
//				// everyone can access these requests
//				.permitAll()
//		.and()
//			.logout()
//				// to logout
//				.invalidateHttpSession(true)
//				// with CSRF we need to map the POST request /logout
//				// if CSRF is disabled the GET request /logout is mapped by default
//				// to an internal Spring Security handler method
//				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//				.logoutSuccessUrl("/login")
//				.permitAll()
//		// AUTHORIZATION
//		.and()
//			.authorizeRequests()
//				.antMatchers("/product/**").hasRole("USER")  
//				// TODO: exercise 6 Task 3
//				  
//				.antMatchers("/test2/**").hasAnyRole("USER")
//				// END exercise 6 Task 3
//				.anyRequest().authenticated() // all requests ABOVE this statement require authentication
//		.and()
//			// to redirect the user when trying to access a resource to which access is not granted
//	        .exceptionHandling().accessDeniedPage("/access-denied");
//    }
//
// 	@Autowired 
// 	private UserDetailsService userDetailsService; 	
//
//     @Autowired
// 	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// 		BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
// 		auth.userDetailsService(userDetailsService).passwordEncoder(pe);
//		
// 	}
//    
// }
