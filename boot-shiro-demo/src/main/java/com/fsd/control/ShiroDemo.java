package com.fsd.control;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
import com.fsd.service.utils.LoginHandler;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sun.tools.classfile.InnerClasses_attribute.Info;

@RestController
@Transactional
public class ShiroDemo {
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

	@Autowired
	private LoginHandler loginHandler;

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	private JPAQueryFactory queryFactory;

	@PostConstruct
	public void init() {
		queryFactory = new JPAQueryFactory(entityManager);
	}

	private Logger logger = LoggerFactory.getLogger(ShiroDemo.class);

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

	@RequestMapping(value = "/shiro/ini", method = RequestMethod.GET)
	@ResponseBody
	public String iniData() {
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
		return "shiro/ini.test";
	}

	@RequestMapping(value = "/shiro/test/{username}", method = RequestMethod.GET)
	@ResponseBody
	public String testData(@PathVariable String username) {
		PermissionService permissionService = new PermissionServiceImpl(permissionRepository);
		RoleService roleService = new RoleServiceImpl(roleRepository, permissionRepository, rolePermissionRepository);
		UserService userService = new UserServiceImpl(userRepository, roleRepository, userRoleRepository, queryFactory);

		List<String> entitys = userService.findRoles(username);

		String result = "the user's role list size is : ";

		if (entitys != null) {
			result += entitys.size();
			logger.info("roles : " + entitys + "   size : " + entitys.size());
		} else {
			result += "0";
		}

		return result;
	}

	@RequestMapping(value = "/shiro/findPermissions", method = RequestMethod.GET)
	@ResponseBody
	public String findPermissions() {
		PermissionService permissionService = new PermissionServiceImpl(permissionRepository);
		RoleService roleService = new RoleServiceImpl(roleRepository, permissionRepository, rolePermissionRepository);
		UserService userService = new UserServiceImpl(userRepository, roleRepository, userRoleRepository, queryFactory);

		List<String> entits = userService.findPermissions("zhang");

		if (entits != null) {
			logger.info("roles : " + entits + "   size : " + entits.size());
		}

		return "shiro/findPermissions";
	}

	@RequestMapping(value = "/shiro/correlationPermissions", method = RequestMethod.GET)
	@ResponseBody
	public String correlationPermissions() {
		PermissionService permissionService = new PermissionServiceImpl(permissionRepository);
		RoleService roleService = new RoleServiceImpl(roleRepository, permissionRepository, rolePermissionRepository);
		UserService userService = new UserServiceImpl(userRepository, roleRepository, userRoleRepository, queryFactory);

		Long roleId = 5L;
		Long permissionId1 = 3L;
		Long permissionId2 = 4L;

		roleService.correlationPermissions(roleId, permissionId1, permissionId2);

		return "shiro/correlationPermissions";
	}

	@RequestMapping(value = "/shiro/uncorrelationPermissions", method = RequestMethod.GET)
	@ResponseBody
	public String uncorrelationPermissions() {
		PermissionService permissionService = new PermissionServiceImpl(permissionRepository);
		RoleService roleService = new RoleServiceImpl(roleRepository, permissionRepository, rolePermissionRepository);
		UserService userService = new UserServiceImpl(userRepository, roleRepository, userRoleRepository, queryFactory);

		Long roleId = 5L;
		Long permissionId1 = 3L;

		roleService.uncorrelationPermissions(roleId, permissionId1);

		return "shiro/correlationPermissions";
	}

	@RequestMapping(value = "/shiro/addPermission", method = RequestMethod.GET)
	@ResponseBody
	public String addPermission() {
		PermissionService permissionService = new PermissionServiceImpl(permissionRepository);
		RoleService roleService = new RoleServiceImpl(roleRepository, permissionRepository, rolePermissionRepository);
		UserService userService = new UserServiceImpl(userRepository, roleRepository, userRoleRepository, queryFactory);

		Permission permission = new Permission("menu:create", "test123", Boolean.TRUE);

		permissionService.createPermission(permission);

		return "shiro/addPermission";
	}

	@RequestMapping(value = "/shiro/deletePermissionById/{sid}", method = RequestMethod.GET)
	@ResponseBody
	public String deletePermissionById(@PathVariable Long sid) {
		PermissionService permissionService = new PermissionServiceImpl(permissionRepository);
		RoleService roleService = new RoleServiceImpl(roleRepository, permissionRepository, rolePermissionRepository);
		UserService userService = new UserServiceImpl(userRepository, roleRepository, userRoleRepository, queryFactory);

		permissionService.deletePermissionById(sid);

		return "shiro/deletePermissionById";
	}

	@RequestMapping(value = "/shiro/loginSuccess")
	@ResponseBody
	public String loginSuccess() {
		loginHandler.loginSuccess();
		return null;
	}
	
	@RequestMapping(value = "/shiro/loginFail")
	@ResponseBody
	public String loginFail() {
		loginHandler.loginFailWithUnknownUsername();
		return null;
	}

}
