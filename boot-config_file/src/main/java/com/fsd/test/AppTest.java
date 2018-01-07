package com.fsd.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

@SpringBootTest
/**
 * SpringApplicationConfiguration
 * 直接用自动生成的两个注解就可以实现测试功能，谢谢。
 * @RunWith(SpringRunner.class)
 * @SpringBootTest
 * 
*/
public class AppTest {
	
	@Autowired
	private BlogProperties blogProperties;
	
	@Test
	public void testConfigfile() {
		System.out.println("name ::: " + blogProperties.getName());
		System.out.println("age : " + blogProperties.getAge());
		System.out.println("author.name : " +  blogProperties.getAuthName());
	}

}
