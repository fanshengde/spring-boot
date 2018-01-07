package com.fsd.dao;

import java.util.List;
import java.util.Map;

import com.fsd.bean.UserInfo;
public interface UserInfoDao {
	public List<UserInfo> getByMap(Map<String, Object> map);
	public UserInfo getById(Integer id);
	public int create(UserInfo userInfo);
	public int update(UserInfo userInfo);
	public int delete(Integer id);
	public UserInfo getByUserName(String userName);
}
