package com.fsd.role;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleDemo {
	Logger logger = LoggerFactory.getLogger(RoleDemo.class);

	public Subject login(String iniPath, String username, String password) {
		// 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
		Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniPath);

		// 2、得到SecurityManager实例 并绑定给SecurityUtils
		SecurityManager securityManager = factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);

		// 3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		subject.login(token);
		return subject;
	}

//	@Test
	public void testHasRole() {

		Subject subject = login("shiro-role.ini", "zhang", "123");
		// Subject subject = login("shiro-role.ini", "wang", "123");

		/**
		 * 1基于角色的访问控制（即隐式角色）
		 * 
		 * 1.1: Shiro 提供了 hasRole/hasRole 用于判断用户是否拥有某个角色/某些权限； 但是没有提供如 hashAnyRole
		 * 用于判断是否有某些权限中的某一个。
		 */

		// 判断拥有角色：role1
		Assert.assertTrue(subject.hasRole("role1"));
		// 判断拥有角色：role1 and role2
		Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
		// 判断拥有角色：role1 and role2 and !role3
		boolean[] results = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));

		logger.info("user result size is : " + results.length);

		for (boolean result : results) {
			logger.info("result is " + result);
		}

		/**
		 * 1.2 Shiro 提供的 checkRole/checkRoles 和 hasRole/hasAllRoles 不同的地方是它在判断为假的情况下会抛出
		 * UnauthorizedException 异常。
		 */
		subject.checkRole("role1");

		subject.checkRoles("role1", "role3");
	}
	
	/**
	 * 2： 基于资源的访问控制（显示角色）
	*/
	@Test
	public void testIsPermitted() {
		/* 2.1：
		 * Shiro 提供了 isPermitted 和 isPermittedAll 用于判断用户是否拥有某个权限或所有权限，
		 * 也没有提供如 isPermittedAny 用于判断拥有某一个权限的接口。
		 * 
		 * 一般规则是“资源标识符：操作”，即是资源级别的粒度；
		 * 这种方式的好处就是如果要修改基本都是一个资源级别的修改，
		 * 不会对其他模块代码产生影响，粒度小。但是实现起来可能稍微复杂点，
		 * 需要维护“用户——角色，角色——权限（资源：操作）”之间的关系。 
		*/
		Subject subject = login("shiro-permission.ini", "zhang", "123");
		// 判断拥有权限：user:create
		Assert.assertTrue(subject.isPermitted("user:create"));
		// 判断拥有权限：user:update and user:delete
		Assert.assertTrue(subject.isPermittedAll("user:update", "user:delete"));
		// 判断没有权限：user:view
		Assert.assertFalse(subject.isPermitted("user:view"));
		
		
		//断言拥有权限：user:create		单个资源单个权限
        subject.checkPermission("user:create");
        //断言拥有权限：user:delete and user:update		单个资源多个权限
        subject.checkPermissions("user:delete", "user:update");
        //断言拥有权限：user:view 失败抛出异常
        subject.checkPermissions("user:view");
	}
}
