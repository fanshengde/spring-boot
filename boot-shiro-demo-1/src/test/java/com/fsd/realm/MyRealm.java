package com.fsd.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class MyRealm implements Realm {
	public static Logger logger = LoggerFactory.getLogger(MyRealm.class);

	@Override
	public String getName() {
		return MyRealm.class.getSimpleName();
	}
	
	@Override
	public boolean supports(AuthenticationToken token) {
		// 仅支持UsernamePasswordToken类型的Token
		return token instanceof UsernamePasswordToken;
	}

	
	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		if (!"zhang".equals(username)) {
			throw new UnknownAccountException();
		}

		if (!"123".equals(password)) {
			throw new IncorrectCredentialsException();
		}
		return new SimpleAuthenticationInfo(username, password, getName());
	}

	@Test
	public void testHello() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> facotry = new IniSecurityManagerFactory("shiro-realm.ini");

		// 2、 securityManager = factory.getInstance();
		SecurityManager securityManager = facotry.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
		
		//4:myRealm限制
//		supports(token);
//		getAuthenticationInfo(token);

		try {
			// 4、登录，即身份验证
			 subject.login(token);
			// logger.info("登陆成功");
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			/**
			 * DisabledAccountException（禁用的帐号） LockedAccountException（锁定的帐号）、
			 * UnknownAccountException（错误的帐号）、 ExcessiveAttemptsException（登录失败次数过多）、
			 * IncorrectCredentialsException （错误的凭证）、 ExpiredCredentialsException（过期的凭证）等；
			 */
		}
		Assert.isTrue(subject.isAuthenticated(), "登陆失败");

		// 6、退出

		subject.logout();
	}

}
