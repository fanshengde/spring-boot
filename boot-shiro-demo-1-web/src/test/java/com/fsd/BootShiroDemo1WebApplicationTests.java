package com.fsd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsd.entity.SysUser;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BootShiroDemo1WebApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	
	@Test
	public void generateJson(Object obj) throws JsonProcessingException {
		SysUser user = new SysUser();
		user.setName("fanshengde");
		user.setUsername("fsd");
		user.setPassword("123");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		System.out.println(json);
	}
	
	public static void main(String[] args) throws JsonProcessingException {
		SysUser user = new SysUser();
		user.setName("fanshengde");
		user.setUsername("fsd");
		user.setPassword("123");
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
		System.out.println(json);
	}

}
