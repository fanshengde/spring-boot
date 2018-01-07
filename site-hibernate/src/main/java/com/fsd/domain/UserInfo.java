package com.fsd.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class UserInfo {
	private Long sid;
	private String userName;
	private String userPassword;
	private String sex;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sid")
	public Long getSid() {
		return sid;
	}
	
	public void setSid(Long sid) {
		this.sid = sid;
	}
	
	@Column(name="user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Column(name="sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name="user_password")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}
