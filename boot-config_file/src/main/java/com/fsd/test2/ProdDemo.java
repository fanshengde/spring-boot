package com.fsd.test2;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdDemo implements EmailService{

	@Override
	public void send() {
		System.out.println("生产环境下");
	}

}
