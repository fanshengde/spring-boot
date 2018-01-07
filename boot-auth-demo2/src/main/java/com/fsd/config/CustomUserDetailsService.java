package com.fsd.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fsd.entity.user.RoleInfo;
import com.fsd.entity.user.SecurityUser;
import com.fsd.entity.user.UserInfo;
import com.fsd.service.UserInfoService;

public class CustomUserDetailsService implements UserDetailsService{
	@Autowired //数据库服务类
	private UserInfoService userInfoService;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
	    //SUser对应数据库中的用户表，是最终存储用户和密码的表，可自定义
        //本例使用SUser中的email作为用户名:
		UserInfo user = userInfoService.findUserByEmail(userName);
		
		if(user == null) {
			throw new UsernameNotFoundException("userName " + userName + " not found");
		}
//		RoleInfo role = new RoleInfo();
//		role.setName("user");
//		role.setUserInfo(user);
//		Set<RoleInfo> roles = new HashSet<RoleInfo>();
//		user.setRoleInfos(roles);
		
		// SecurityUser实现UserDetails并将SUser的Email映射为username
		SecurityUser securityUser = new SecurityUser(user);
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("user"));
		return securityUser;
	}
}
