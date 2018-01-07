package com.fsd.dao;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsd.entity.User;

@Repository
@Table(name="user_info")
@Qualifier("userRepository")
public interface IUserDao {
	
	@Query("select o from User t where o.username=:username")
	User findByname(@Param("username") String loginName);

	Object getList();

}
