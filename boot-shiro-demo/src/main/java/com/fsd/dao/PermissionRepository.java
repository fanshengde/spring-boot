package com.fsd.dao;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.entity.Permission;


public interface PermissionRepository extends PagingAndSortingRepository<Permission, Long>, QuerydslPredicateExecutor<Permission>{

}
