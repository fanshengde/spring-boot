package com.fsd.dao;

import java.util.List;
import java.util.Map;

import com.fsd.bean.UserInfo;

public interface UserInfoDao {
	List<UserInfo> getByMap(Map<String, Object> mpa	);
	List<UserInfo> getByRoleInfoId(Map<String, Object > map);
	UserInfo getById(Integer sid);
	Integer create(UserInfo userInfo);
	UserInfo getByUserName(String userName);
}
