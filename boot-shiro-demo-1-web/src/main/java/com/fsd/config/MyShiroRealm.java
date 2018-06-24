package com.fsd.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fsd.entity.SysPermission;
import com.fsd.entity.SysRole;
import com.fsd.entity.SysUser;
import com.fsd.entity.repository.SysUserRepository;

public class MyShiroRealm extends AuthorizingRealm {
	static final Logger LOG = LoggerFactory.getLogger(MyShiroRealm.class);

	@Autowired
	SysUserRepository sysUserRepository;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		LOG.info("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		for (SysRole role : user.getRoleList()) {
			authorizationInfo.addRole(role.getRole());
			for (SysPermission permission : role.getPermissions()) {
				authorizationInfo.addStringPermission(permission.getPermission());
			}
		}
		return authorizationInfo;
	}

	/* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		LOG.info("主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确 MyShiroRealm.doGetAuthenticationInfo()");

		String username = (String) token.getPrincipal();
		System.out.println(token.getCredentials());

		// 通过username从数据库中查找 User对象，如果找到，没找到.
		// 实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法

		SysUser user = sysUserRepository.findByUsername(username);

		LOG.info("----->>userInfo=" + user);

		if (user == null) {
			return null;
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()), getName());

		return authenticationInfo;
	}

}
