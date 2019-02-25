package springData;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/error").setViewName("error");
		registry.addViewController("/login").setViewName("login");

	}

	// Handles HTTP GET requests for /resources/** by efficiently serving up static
	// resources in the ${webappRoot}/resources/ directory
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/src/main/resources/static");
	}

	// Java configuration equivalent to
	// <mvc:default-servlet-handler/> in spring-servlet.xml
	// used to use bootstrap when security is enabled
	// @Override
	// public void configureDefaultServletHandling(DefaultServletHandlerConfigurer
	// configurer) {
	// configurer.enable();
	// }

	// Resolves views selected for rendering by @Controllers to .jsp resources in
	// the
	// /WEB-INF/views directory
	// @Bean
	// public InternalResourceViewResolver viewResolver() {
	// InternalResourceViewResolver viewResolver = new
	// InternalResourceViewResolver();
	// viewResolver.setViewClass(JstlView.class);
	// viewResolver.setPrefix("/WEB-INF/views/");
	// viewResolver.setSuffix(".jsp");
	// viewResolver.setOrder(2);
	// return viewResolver;
	// }

	// Alternative using ViewResolver
	//
	// public ViewResolver internalResourceViewResolver() {
	// InternalResourceViewResolver bean = new InternalResourceViewResolver();
	// bean.setViewClass(JstlView.class);
	// bean.setPrefix("/WEB-INF/views/");
	// bean.setSuffix(".jsp");
	// return bean;
	// }

}