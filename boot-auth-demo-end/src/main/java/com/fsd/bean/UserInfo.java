package com.fsd.bean;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserInfo implements UserDetails {
	private Integer sid;
	private String loginName;
	private String username;
	@JsonIgnore
	private String password;
	private String rePassword;
	private String historyPassword;
	private String email;
	@JsonIgnore
	private String telephone;
	private String mobilePhone;
	private List<? extends GrantedAuthority> authorities;
	private RoleInfo roleInfo;
	private Integer roleId;

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
    public void setGrantedAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

	@Override
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@Override
	@JsonIgnore
	public String getUsername() {
		return username;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	public String getHistoryPassword() {
		return historyPassword;
	}

	public void setHistoryPassword(String historyPassword) {
		this.historyPassword = historyPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public RoleInfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAuthorities(List<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String toString() {
		return "User{" + "sid=" + sid + ", loginName=" + loginName + ", userName=" + username + ", password=" + password
				+ ", email=" + email + ", telephone=" + telephone + ", mobilePhone=" + mobilePhone + '}';
	}
}
