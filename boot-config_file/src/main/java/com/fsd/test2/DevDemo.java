package com.fsd.test2;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevDemo implements EmailService{

	@Override
	public void send() {
		System.out.println("开发环境下");
	}

}
