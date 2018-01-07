package com.fsd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BootLogApplication {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public static void main(String[] args) {
		new BootLogApplication().testLogger();
		SpringApplication.run(BootLogApplication.class, args);
	}
	
	public void testLogger() {
		 logger.debug("111111111111111111111 日志输出测试 Debug");
		 logger.info("2222222222222222222222222 日志输出测试 info");
	}
}
