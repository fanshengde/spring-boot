package com.fsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.fsd")
@EnableAutoConfiguration
@EntityScan(basePackages="com.fsd.pojo")
@EnableJpaRepositories(basePackages="com.fsd.dao.repository")

public class SpringData1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringData1Application.class, args);
	}
}
