package com.fsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.fsd")
@EntityScan(basePackages = "com.fsd.bean")
@EnableJpaRepositories(basePackages = "com.fsd.dao")
public class BootAuthDemoEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootAuthDemoEndApplication.class, args);
	}
}
