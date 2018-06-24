package com.fsd.realm2;

import java.util.Collection;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Authenticator 及 AuthenticationStrategy fsd-未成功
 * */
public class Realm2Demo implements AuthenticationStrategy {
	private static final Logger logger = LoggerFactory.getLogger(Realm2Demo.class);

	private void login(String configFile) {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("zhang@163.com", "123");
		subject.login(token);
	}

	@Test
	public void testAllSuccessfulStrategyWithSucess() {
		login("shiro-realm-2.ini");

		Subject subject = SecurityUtils.getSubject();

		PrincipalCollection principalCollection = subject.getPrincipals();
		logger.info("auth info size: " + principalCollection.asList().size());
	}

	// 在每个Realm之前调用
	@Override
	public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate)
			throws AuthenticationException {
		return null;
	}

	// 在每个Realm之后调用
	@Override
	public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo,
			AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
		return new SimpleAuthenticationInfo();
	}

	// 在所有Realm之后调用
	@Override
	public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}
}
