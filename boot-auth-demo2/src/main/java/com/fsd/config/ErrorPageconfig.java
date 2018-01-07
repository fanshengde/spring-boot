package com.fsd.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer; 
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer; 
import org.springframework.boot.web.servlet.ErrorPage; 
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;


public class ErrorPageconfig {
	
	@Bean
	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
		return new MyCustomizer();
	}
	
	private static class MyCustomizer implements EmbeddedServletContainerCustomizer{
		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403"));
		}
	}
}
