package com.fsd.service.impl;

import java.util.List;

import com.fsd.dao.RoleRepository;
import com.fsd.dao.UserRepository;
import com.fsd.dao.UserRoleRepository;
import com.fsd.entity.QPermission;
import com.fsd.entity.QRole;
import com.fsd.entity.QRolePermission;
import com.fsd.entity.QUser;
import com.fsd.entity.QUserRole;
import com.fsd.entity.Role;
import com.fsd.entity.User;
import com.fsd.entity.UserRole;
import com.fsd.service.UserService;
import com.querydsl.jpa.impl.JPAQueryFactory;

public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private UserRoleRepository userRoleRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			UserRoleRepository userRoleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
	}

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			UserRoleRepository userRoleRepository, JPAQueryFactory queryFactory) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.userRoleRepository = userRoleRepository;
		this.queryFactory = queryFactory;
	}

	private JPAQueryFactory queryFactory;

	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void updateUser(User user) {
		QUser quser = QUser.user;
		queryFactory.update(quser).where(quser.sid.eq(user.getSid()));
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public void changePassword(User user, String newPassword) {
		// userRepository.
	}

	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		if (roleIds == null || roleIds.length == 0) {
			return;
		}

		User user = userRepository.findById(userId).get();
		Role role;
		UserRole userRole;

		for (Long roleId : roleIds) {
			role = roleRepository.findById(roleId).get();
			userRole = new UserRole(user, role);
			userRoleRepository.save(userRole);
		}
	}

	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		if (roleIds == null || roleIds.length == 0) {
			return;
		}

		User user = userRepository.findById(userId).get();
		Role role;
		UserRole userRole;

		for (Long roleId : roleIds) {
			role = roleRepository.findById(roleId).get();
			userRole = new UserRole(user, role);
			userRoleRepository.delete(userRole);
		}
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<String> findRoles(String username) {
		QUser quser = QUser.user;
		QRole qRole = QRole.role1;
		QUserRole qUserRole = QUserRole.userRole;
		return queryFactory.select(qRole.role).from(qRole, quser, qUserRole).where(
				quser.username.eq(username).and(quser.sid.eq(qUserRole.user.sid)).and(qRole.sid.eq(qUserRole.role.sid)))
				.fetch();
	}

	@Override
	public List<String> findPermissions(String username) {
		QUser qUser = QUser.user;
		QRole qRole = QRole.role1;
		QUserRole qUserRole = QUserRole.userRole;
		QPermission qPermission = QPermission.permission1;
		QRolePermission qRolePermission = QRolePermission.rolePermission;

		return queryFactory.select(qPermission.permission).from(qPermission, qUser, qUserRole, qRole, qRolePermission)
				.where(qUser.username.eq(username).and(qUser.sid.eq(qUserRole.user.sid))
						.and(qRole.sid.eq(qUserRole.role.sid)).and(qRole.sid.eq(qRolePermission.role.sid))
						.and(qPermission.sid.eq(qRolePermission.permission.sid)))
				.fetch();

	}

	@Override
	public User findOne(Long userId) {
		return userRepository.findById(userId).get();
	}

}
