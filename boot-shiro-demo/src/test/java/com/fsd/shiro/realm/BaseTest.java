package com.fsd.shiro.realm;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fsd.dao.PermissionRepository;
import com.fsd.dao.RolePermissionRepository;
import com.fsd.dao.RoleRepository;
import com.fsd.dao.UserRepository;
import com.fsd.dao.UserRoleRepository;
import com.fsd.entity.Permission;
import com.fsd.entity.Role;
import com.fsd.entity.User;
import com.fsd.service.PermissionService;
import com.fsd.service.RoleService;
import com.fsd.service.UserService;
import com.fsd.service.impl.PermissionServiceImpl;
import com.fsd.service.impl.RoleServiceImpl;
import com.fsd.service.impl.UserServiceImpl;

public class BaseTest {

	@Autowired
	PermissionRepository permissionRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolePermissionRepository rolePermissionRepository;

	@Autowired
	private UserRoleRepository userRoleRepository;

	protected String password = "123";

	protected Permission p1;
	protected Permission p2;
	protected Permission p3;

	protected Role r1;
	protected Role r2;
	protected Role r3;

	protected User u1;
	protected User u2;
	protected User u3;
	protected User u4;

	@Test
	public void iniData() {
		PermissionService permissionService = new PermissionServiceImpl(permissionRepository);
		RoleService roleService = new RoleServiceImpl(roleRepository, permissionRepository, rolePermissionRepository);
		UserService userService = new UserServiceImpl(userRepository, roleRepository, userRoleRepository);

		// 1、新增权限
		p1 = new Permission("user:create", "用户模块新增", Boolean.TRUE);
		p2 = new Permission("user:update", "用户模块修改", Boolean.TRUE);
		p3 = new Permission("menu:create", "菜单模块新增", Boolean.TRUE);

		permissionService.createPermission(p1);
		permissionService.createPermission(p2);
		permissionService.createPermission(p3);

		// 2、新增角色
		r1 = new Role("admin", "管理员", Boolean.TRUE);
		r2 = new Role("user", "用户管理员", Boolean.TRUE);
		roleService.createRole(r1);
		roleService.createRole(r2);

		// 3、关联角色-权限
		roleService.correlationPermissions(r1.getSid(), p1.getSid());
		roleService.correlationPermissions(r1.getSid(), p2.getSid());
		roleService.correlationPermissions(r1.getSid(), p3.getSid());

		roleService.correlationPermissions(r2.getSid(), p1.getSid());
		roleService.correlationPermissions(r2.getSid(), p2.getSid());

		// 4、新增用户
		u1 = new User("zhang", password);
		u2 = new User("li", password);
		u3 = new User("wu", password);
		u4 = new User("wang", password);
		u4.setLocked(Boolean.TRUE);
		userService.createUser(u1);
		userService.createUser(u2);
		userService.createUser(u3);
		userService.createUser(u4);

		// 5、关联用户-角色
		userService.correlationRoles(u1.getSid(), r1.getSid());
	}
}
