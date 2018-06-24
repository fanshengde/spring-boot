package com.fsd.service;

import java.util.List;

import com.fsd.entity.User;

public interface UserService {
	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	public User createUser(User user);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	public void changePassword(User user, String newPassword);

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param user
	 */
	public void deleteUser(User user);

	/**
	 * 添加用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void correlationRoles(Long userId, Long... roleIds);

	/**
	 * 移除用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	public void uncorrelationRoles(Long userId, Long... roleIds);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	public List<String> findRoles(String username);

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	public List<String> findPermissions(String username);

	/**
	 * 根据ID查找用户
	 * 
	 * @param username
	 * @return
	 */
	public User findOne(Long userId);
}
