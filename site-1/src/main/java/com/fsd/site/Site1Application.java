package com.fsd.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@PropertySource("classpath:config/config.properties")

@RestController

@SpringBootApplication
public class Site1Application {
	@Autowired
	private Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(Site1Application.class, args);
	}

	@RequestMapping("test")
	public String index() {
		String index = env.getProperty("test1");
		return index;
	}
	
	
}
