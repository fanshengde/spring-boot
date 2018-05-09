package com.fsd.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fsd.bean.base.AbstractEntity;

@Entity
public class UserInfo extends AbstractEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String cnname;
	private String username;

	private String password;
	private String rePassword;
	private String historyPassword;
	private String email;
	private String telephone;
	private String mobilePhone;
	private String wechatId;
	private Long loginCount;
	@Transient
	private List<? extends GrantedAuthority> authorities;

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
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		return true;
	}

	public String getCnname() {
		return cnname;
	}

	public void setCnname(String cnname) {
		this.cnname = cnname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public Long getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}

	@JsonIgnore
	public List<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setGrantedAuthorities(List<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public String toString() {
		return "User{" + "sid=" + getSid() + ", cnName=" + cnname + ", userName=" + username + ", password=" + password
				+ ", email=" + email + ", telephone=" + telephone + ", mobilePhone=" + mobilePhone + '}';
	}
}
