package com.fsd.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


//@Table(name = "role_info", catalog="test")
@Entity
@Table(name="role_info")
public class RoleInfo {
	private Integer sid;
	private UserInfo userInfo;
	private String name;

	public RoleInfo() {
		
	}
	
	public RoleInfo(UserInfo userInfo) {
//		this.userInfo = userInfo;
	}
	
	public RoleInfo(UserInfo userInfo, String name) {
//		this.userInfo = userInfo;
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sid", unique = true, nullable = false)
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}
	
	@Column(name = "username", length = 20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable =false)
	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
}
