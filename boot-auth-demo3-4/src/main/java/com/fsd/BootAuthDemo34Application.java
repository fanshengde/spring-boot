package com.fsd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages ="com.fsd")
@SpringBootApplication
public class BootAuthDemo34Application {

	public static void main(String[] args) {
		SpringApplication.run(BootAuthDemo34Application.class, args);
	}
}
