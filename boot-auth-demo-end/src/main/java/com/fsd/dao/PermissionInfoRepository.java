package com.fsd.dao;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.bean.PermissionInfo;

public interface PermissionInfoRepository
		extends PagingAndSortingRepository<PermissionInfo, Long>, QuerydslPredicateExecutor<PermissionInfo> {

}