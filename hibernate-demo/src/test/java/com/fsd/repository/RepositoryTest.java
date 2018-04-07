package com.fsd.repository;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fsd.pojo.UserInfo;
import com.fsd.util.ApplicationContextUtil;

public class RepositoryTest {

//	@Autowired
//	public UserRepository userRepository;
	
	@Test
	public void userSave() {
		ApplicationContextUtil.getBean("userRepository");
//		UserInfo u = getUserInfo(2L, "da", "n", "fan 1", null);
//		System.out.println(u.toString());
//		userRepository.save(u);

	}
	
//	@Test
	public void userGet() {
//		UserInfo u = userRepository.findOne(123L);
//		System.out.println(u.toString());
	}

	public UserInfo getUserInfo(Long sid, String address, String sex, String username, Date birthday) {
		UserInfo u = new UserInfo();
		u.setSid(sid);
		u.setAddress(address);
		u.setBirthday(birthday);
		u.setUsername(username);
		u.setSex(sex);
		return u;
	}
}
