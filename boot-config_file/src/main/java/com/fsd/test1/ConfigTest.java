package com.fsd.test1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigTest {
	// my.name=fanshengde
	// my.age=3333
	// my.author.name = fan
	@Value("${my.name}")
	private String name;

	@Value("${my.age}")
	private String age;

	@Value("${my.author.name}")
	private String authName;

	// my.gs = "th"
	// my.work = ${my.name} working at ${my.gs}
	@Value("${my.work}")
	private String working;

	// my.random.value = ${random.value}
	// my.random.number = ${random.int}
	// my.random.bignumber = ${random.long}
	// my.random.fiexNum = ${random.int(10)}
	// my.random.fiexNum2 = ${random.int[10, 20]}
	@Value("${my.random.value}")
	private String random_value;
	
	@Value("${my.random.number}")
	private int random_number;
	
	@Value("${my.random.bignumber}")
	private long random_bignumber;
	
	@Value("${my.random.fiexNum}")
	private int random_fiexNum;

	@Value("${my.random.fiexNum2}")
	private int random_fiexNum2;

	@Test
	public void test() {

		System.out.println("name ::: " + name);
		System.out.println("age : " + age);
		System.out.println("author.name : " + authName);

		System.out.println("author.name : " + working);

		System.out.println("random_value  ：  " + random_value);
		System.out.println("random_number  ：  " + random_number);
		System.out.println("random_bignumber  ：  " + random_bignumber);
		System.out.println("random_fiexNum  ：  " + random_fiexNum);
		System.out.println("random_fiexNum  ：  " + random_fiexNum2);
	}

}
