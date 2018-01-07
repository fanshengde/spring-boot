package com.fsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

@ComponentScan(basePackages = "com.fsd")
@EnableAutoConfiguration
@EntityScan(basePackages="com.fsd.pojo")
@EnableJpaRepositories(basePackages="com.fsd.repository")

@RestController

public class HibernateDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateDemoApplication.class, args);
	}
	
	@RequestMapping("/test")
	public String test() {
		return "hello";
	}
}
