package com.fsd.dao;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.bean.RoleInfo;

public interface RoleInfoRepository
		extends PagingAndSortingRepository<RoleInfo, Long>, QuerydslPredicateExecutor<RoleInfo> {

}
