package com.fsd.dao;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.entity.Role;


public interface RoleRepository extends PagingAndSortingRepository<Role, Long>, QuerydslPredicateExecutor<Role>{

}
