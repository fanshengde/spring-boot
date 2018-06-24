package com.fsd.dao;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.entity.UserRole;


public interface UserRoleRepository extends PagingAndSortingRepository<UserRole, Long>, QuerydslPredicateExecutor<UserRole>{

}
