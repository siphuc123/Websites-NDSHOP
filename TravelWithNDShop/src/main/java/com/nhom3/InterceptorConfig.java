package com.nhom3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nhom3.interceptor.Globallnterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Autowired
	Globallnterceptor globallnterceptor;
	
	 private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
         "classpath:/META-INF/resources/", "classpath:/resources/",
         "classpath:/static/", "classpath:/public/" };

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globallnterceptor).addPathPatterns("/**")
		.excludePathPatterns("/rest/**", "/admin/**","/assets/**", "/resources/**", "/static/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
	}
}
