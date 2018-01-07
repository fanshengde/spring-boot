package com.fsd.test2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestDemo {
	
//	@Value("${my.random.fiexNum2}")
	@Value("${server.port}")
	private int servicePort;
	
	@Value("${test.msg}")
	private String message;
	
	@Test
	public void test() {
		System.out.print("server.port11: ");
		System.out.println(servicePort);
		System.out.print("testMessage: ");
		System.out.println(message);
	}
}
