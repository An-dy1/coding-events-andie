package org.launchcode.codingevents;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// self-explanatory annotation
// web mvc configurer calls addInterceptors during startup
@Configuration
public class WebApplicationConfig implements WebMvcConfigurer {

	// a Spring-managed class
	@Bean
	public AuthenticationFilter authenticationFilter() {
		return new AuthenticationFilter();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationFilter());
	}
}
