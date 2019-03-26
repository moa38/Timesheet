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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

//      protected void configure(HttpSecurity http) throws Exception {
//     	http
//     	 .requiresChannel()
// 	    		.anyRequest()
// 	    		.requiresSecure()
// 	    	.and()
// 		    .formLogin()
// 		    	.loginPage("/login") 
// 				.loginProcessingUrl("/login")
// 				.defaultSuccessUrl("/add-shift",true)
// 				.failureUrl("/login")
// 				.permitAll()
// 		.and()
// 			.logout()
// 				.invalidateHttpSession(true)
// 				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
// 				.logoutSuccessUrl("/add-shift")
// 				.permitAll()
// 		.and()
// 			.authorizeRequests()
// 				.antMatchers("/login/**").hasRole("USER")  
// 				.anyRequest().authenticated() 
// 		.and()
			
// 	        .exceptionHandling().accessDeniedPage("/access-denied");
//     }

// 	@Autowired 
// 	private UserDetailsService userDetailsService; 	

// }






    

   




