package com.fsd.dao;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.bean.UserInfo;

public interface UserInfoRepository
		extends PagingAndSortingRepository<UserInfo, Long>, QuerydslPredicateExecutor<UserInfo> {
	UserInfo findByUsername(String username);
}
