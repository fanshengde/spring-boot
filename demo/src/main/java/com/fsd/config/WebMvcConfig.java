package com.fsd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		/**
		 * 具体的作用是什么？
		 * */
		System.out.println("login ------fff");
		registry.addViewController("/login").setViewName("login");
	}
}
