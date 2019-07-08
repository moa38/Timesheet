package springData;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   @Autowired UserDetailsService userDetailsService;
   @Autowired DataSource dataSource;

   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
      BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
      return pe;
   }

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
      .authorizeRequests()
         .antMatchers("/", "/index").permitAll()
         .anyRequest().authenticated()
         .and()
      .formLogin()
         .loginProcessingUrl("/j_spring_security_check")
         .loginPage("/login")
         .usernameParameter("username").passwordParameter("password")
         .defaultSuccessUrl("/add-timesheet", true)
         .permitAll()
         .and()
      .logout()
         .permitAll();

      http.authorizeRequests().and() //
      .rememberMe().tokenRepository(this.persistentTokenRepository()) //
      .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
   }

   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

      // Setting Service to find User in the database.
      // And Setting PassswordEncoder
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

   }

   @Bean
   public PersistentTokenRepository persistentTokenRepository() {
      JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
      db.setDataSource(dataSource);
      return db;
   }
}
