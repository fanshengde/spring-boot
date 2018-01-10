package com.fsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.fsd")
@SpringBootApplication
public class BootAuthDemoEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootAuthDemoEndApplication.class, args);
	}
}