package com.fsd.dao;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fsd.entity.User;


public interface UserRepository extends PagingAndSortingRepository<User, Long>, QuerydslPredicateExecutor<User>{

	User findByUsername(String username);

}
