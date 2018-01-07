package com.fsd.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="t_user")
public class UserInfo {
	private Long sid;
	private String username;
	private Date birthday;
	private String sex;
	private String address;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sid")
	public Long getSid() {
		return sid;
	}
	
	public void setSid(Long sid) {
		this.sid = sid;
	}
	
	@Column(name ="username")
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="birthday")
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Column(name = "sex")
	public String getSex() {
		return sex;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		 return "User [id=" + sid + ", username=" + username + ", birthday="  
	                + birthday + ", sex=" + sex + ", address=" + address + "]"; 
	}
	
}
