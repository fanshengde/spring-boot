package com.fsd.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fsd.bean.PermissionInfo;
import com.fsd.bean.RoleInfo;
import com.fsd.bean.UserInfo;
import com.fsd.dao.PermissionInfoDao;
import com.fsd.dao.UserInfoDao;

public class ShiroRealm extends AuthorizingRealm {
//	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private PermissionInfoDao permissionInfoDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//		logger.info("doGetAuthorizationInfo " + principalCollection.getPrimaryPrincipal());
		System.out.println("doGetAuthorizationInfo " + principalCollection.getPrimaryPrincipal());
		UserInfo userInfo = userInfoDao.getByUserName((String) principalCollection.getPrimaryPrincipal());

		// 把principals放session中 key=userId value=principals
		SimpleAuthorizationInfo simpleAuthorization = new SimpleAuthorizationInfo();

		// 赋予角色
		for (RoleInfo roleInfo : userInfo.getRoles()) {
			simpleAuthorization.addRole(roleInfo.getmName());
		}
		
		// 赋予权限
		for(PermissionInfo permissionInfo : permissionInfoDao.getByUserId(userInfo.getmId())) {
			if(StringUtils.isNotBlank(permissionInfo.getmName())){
				simpleAuthorization.addStringPermission(permissionInfo.getmName());
			}
		}
		//设置登录次数、时间
		userInfoDao.update(userInfo);		
		return simpleAuthorization;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//		logger.info("doGetAuthenticationInfo + 	" + authenticationToken.toString() );
		System.out.println("doGetAuthenticationInfo + 	" + authenticationToken.toString() );
		UsernamePasswordToken usernamePasswordToken  = (UsernamePasswordToken) authenticationToken;
	
		String userName = usernamePasswordToken.getUsername();
		System.out.println(this.getClass().getName() + " : " + userName + " : " + usernamePasswordToken.getPassword());
//		logger.info(this.getClass().getName() + " : " + userName + " : " + usernamePasswordToken.getPassword());

		UserInfo userInfo = userInfoDao.getByUserName(userName);
		
		if(userInfo != null) {
			//byte[] salt = Encodes.decodeHex(user.getSalt());
			// ShiroUser shiroUser=new ShiroUser(user.getId(), user.getLoginName(), user.getName());
			//设置用户session
			Session session = SecurityUtils.getSubject().getSession();
			session.setAttribute("userInfo", userInfo);
			return new SimpleAuthenticationInfo(userName, userInfo.getPassword(), userInfo.getmName());
		}else {
			return null;
		}
	}
}
