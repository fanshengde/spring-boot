package com.fsd.service.utils;

import java.util.Collection;
import java.util.Set;

import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fsd.entity.User;

@Component
public class LoginHandler extends ShiroBase {
	private final static Logger logger = LoggerFactory.getLogger(LoginHandler.class);
	private String configFile = "classpath:shiro.ini";
	private String username = "zhang";
	private String password = "123";

	public void loginSuccess() {
		login(configFile, username, password);
	}

	public void loginFailWithUnknownUsername() {
		login(configFile, username + 1, password);
	}

	public void loginFailWithErrorPassowrd() {
		login(configFile, username, password + 1);
	}

	public void loginFailWithLocked() {
		username = "wang";
		login(configFile, username, password + 1);
	}

	public void loginFailWithLimitRetryCount() {
		for (int i = 1; i <= 5; i++) {
			try {
				login(configFile, username, password);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		login(configFile, username, password);
	}

	public void hasRole() {
		login(configFile, username, password);
		logger.info(subject().hasRole("admin") + "");
	}

	public void noRole() {
		username = "li";
		login(configFile, username, password);
		logger.info(subject().hasRole("admin") + "");
	}

	public void noPermission() {
		login(configFile, username, password);
		logger.info(subject().isPermitted("user:create") + "");
	}

	public void princialCollection() {
		configFile = "classpath:shiro-multirealm.ini";

		// 因为Realm里没有进行验证，所以相当于每个Realm都身份验证成功了
		login(configFile, username, password);
		Subject subject = subject();

		// 获取Primary Principal（即第一个）
		Object primaryPrincipal1 = subject.getPrincipal();
		PrincipalCollection princialCollection = subject.getPrincipals();
		Object primaryPrincipal2 = princialCollection.getPrimaryPrincipal();

		// 但是因为多个Realm都返回了Principal，所以此处到底是哪个是不确定的
		logger.info(primaryPrincipal1 + "", primaryPrincipal2 + "");

		// 返回 a b c
		Set<String> realmName = princialCollection.getRealmNames();
		logger.info(realmName + "");

		// 因为MyRealm1和MyRealm2返回的凭据都是zhang，所以排重了
		Set<Object> principals = princialCollection.asSet();
		logger.info(principals + "");

		// 根据Realm名字获取
		Collection<User> users = princialCollection.fromRealm("c");
		logger.info(users + "");

	}
}
