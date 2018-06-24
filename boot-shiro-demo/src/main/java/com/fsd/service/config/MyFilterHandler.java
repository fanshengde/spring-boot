package com.fsd.service.config;

import org.apache.shiro.web.env.IniWebEnvironment;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterHandler extends IniWebEnvironment{
	public void test() {
		System.out.println(1111);
	}
}
