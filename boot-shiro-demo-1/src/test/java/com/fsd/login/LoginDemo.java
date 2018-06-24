package com.fsd.login;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.util.Assert;

public class LoginDemo {
	public final static Logger logger = LoggerFactory.getLogger(LoginDemo.class);

	
	/**
	 * testHello() 从如上代码可总结出身份验证的步骤：
	 * 1、收集用户身份 / 凭证，即如用户名 / 密码；
	 * 2、调用 Subject.login 进行登录，如果失败将得到相应的 AuthenticationException 异常，根据异常提示用户错误信息；否则登录成功；
	 * 3、最后调用 Subject.logout 进行退出操作。
	 * */
	@Test
	public void testHello() {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> facotry = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

		// 2、 securityManager = factory.getInstance();
		SecurityManager securityManager = facotry.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

		try {
			// 4、登录，即身份验证
			subject.login(token);
			logger.info("登陆成功");
		} catch (AuthenticationException e) {
			// 5、身份验证失败
			/**
			 * DisabledAccountException（禁用的帐号）
			 * LockedAccountException（锁定的帐号）、
			 * UnknownAccountException（错误的帐号）、
			 * ExcessiveAttemptsException（登录失败次数过多）、
			 * IncorrectCredentialsException （错误的凭证）、
			 * ExpiredCredentialsException（过期的凭证）等；
			 * */
			logger.error("登陆失败");
		}
		Assert.isTrue(subject.isAuthenticated(), "登陆失败");

		// 6、退出

		subject.logout();
	}
}
