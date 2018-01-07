package com.fsd.entity.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="user_info")
public class UserInfo {
	private Integer id;
	private String name;
	private String email;
	private String password;
	private Date dob;
	private Set<RoleInfo> roleInfos = new HashSet<RoleInfo>(0);
	public UserInfo() {
		
	}
	public UserInfo(UserInfo userInfo) {
		if(userInfo != null) {
			this.name = userInfo.getName();
			this.email = userInfo.getEmail();
			this.password = userInfo.getPassword();
			this.dob = userInfo.getDob();
			this.roleInfos = userInfo.getRoleInfos();
		}
	}
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", unique=true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "user_name" , length = 40)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="email", length = 40)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="pass_word", length = 200)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	@OneToMany()
	@JoinColumn(name="user_id")
	public Set<RoleInfo> getRoleInfos() {
		return roleInfos;
	}
	public void setRoleInfos(Set<RoleInfo> roleInfos) {
		this.roleInfos = roleInfos;
	}
}
