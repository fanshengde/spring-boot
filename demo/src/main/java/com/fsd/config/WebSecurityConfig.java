package com.fsd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fsd.domain.SysUser;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	@Autowired
	SysUserDa	public List<Permissiono sysUserDao;
	@Autowired
	private MyFilterSecurityInterceptor myFilterSecurityAdapter;
	
	@Bean
	UserDetailsService customUserService() {
//	返回一个user的对象
		return CustomUserService();
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		SysUser sysUser = sysUserDao.findByUserName(username);
		
		if(sysUser == null) {
			throw new UsernameNotFoundException("用户名不存在");
		}else {
			
		}
	}
	
	
}
