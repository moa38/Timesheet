package springData;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/").setViewName("/login");
      registry.addViewController("/error").setViewName("error");
      registry.addViewController("/login").setViewName("login");
      registry.addViewController("/dashboard").setViewName("user/dashboard");
      registry.addViewController("/add-timesheet").setViewName("user/add-timesheet");
      registry.addViewController("/view-timesheets").setViewName("user/view-timesheets");
      registry.addViewController("/edit-timesheet").setViewName("user/edit-timesheet");
      registry.addViewController("/submit-shift").setViewName("user/submit-shift");
      registry.addViewController("/delete-shift").setViewName("user/delete-shift");
      registry.addViewController("/help").setViewName("help");
      //registry.addViewController("/account").setViewName("account/account");
      //registry.addViewController("/sign-up").setViewName("/sign-up");
   }

   // Handles HTTP GET requests for /resources/** by efficiently serving up static
   // resources in the ${webappRoot}/resources/ directory
   // TODO
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/static/**").addResourceLocations("/src/main/resources/static");
   }

}
