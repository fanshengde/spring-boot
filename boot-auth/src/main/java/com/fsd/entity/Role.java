package com.fsd.entity;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "role_info")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sid;
	
	private String roleName;
	
	@OneToMany(mappedBy = "role", fetch=FetchType.EAGER)
	private List<Permission> permissionList;
	
	@ManyToMany
	@JoinTable(name = "user_role_map", joinColumns= {@JoinColumn(name ="role_id")}, inverseJoinColumns = {@JoinColumn(name="user_id")})
	private List<User> userList;
	
	@Transient
	public List<String> getPermissionsName(){
		List<String> list = new ArrayList<String>();
		List<Permission> perList = getPermissionList();
		for(Permission per : perList) {
			list.add(per.getPermissionName());
		}
		return list;
	}
	
	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<Permission> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
	
}
