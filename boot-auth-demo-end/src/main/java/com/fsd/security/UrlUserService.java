package com.fsd.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fsd.bean.PermissionInfo;
import com.fsd.bean.UserInfo;
import com.fsd.dao.PermissionInfoDao;
import com.fsd.dao.UserInfoDao;


public class UrlUserService implements UserDetailsService{

	@Autowired
	UserInfoDao userInfoDao;
	
	@Autowired
	PermissionInfoDao permissionInfoDao;
	
	/**
	 * 重写ｌｏａｄＵｓｅｒＢｙＵｓｅｒＮａｍｅ方法获得ｕｓｅｒｄｅｔａｉｌｓ类型用户
	 * */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo userInfo = userInfoDao.getByUserName(username);
		
		if(userInfo != null	) {
			List<PermissionInfo> permissionInfos = permissionInfoDao.getByUserSid(userInfo.getSid());
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			
			for(PermissionInfo permissionInfo: permissionInfos	) {
				if(permissionInfo != null && permissionInfo.getSname() != null) {
					GrantedAuthority grantedAuthority = new UrlGrantedAuthority(permissionInfo.getPermissionUrl(), permissionInfo.getMethod());
					grantedAuthorities.add(grantedAuthority);
				}
			}
			userInfo.setGrantedAuthorities(grantedAuthorities);
			return userInfo;
		}else {
			throw new UsernameNotFoundException("admin : " + username +  " do not exist!");
		}
	}

}
