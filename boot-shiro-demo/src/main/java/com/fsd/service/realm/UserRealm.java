package com.fsd.service.realm;

import java.util.HashSet;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.fsd.entity.User;
import com.fsd.service.UserService;

public class UserRealm extends AuthorizingRealm {

	private UserService userService;

	public UserRealm(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();

		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(new HashSet<>(userService.findRoles(username)));
		authorizationInfo.setStringPermissions(new HashSet<>(userService.findPermissions(username)));

		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		User user = userService.findByUsername(username);
		if (user == null) {
			throw new UnknownAccountException(); // 没有找到账号
		}

		if (Boolean.TRUE.equals(user.getLocked())) {
			throw new LockedAccountException(); // 账号锁定
		}

		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
				user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
		return authenticationInfo;
	}

}
