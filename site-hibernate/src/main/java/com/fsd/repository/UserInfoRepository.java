package com.fsd.repository;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.fsd.domain.UserInfo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
@Table(name="user_info")
@Qualifier("userInfoRepository")
public interface UserInfoRepository extends CrudRepository<UserInfo, Long>{
	@Query("select t from UserInfo t where t.userName =:userName")
	public UserInfo findUserByName(@Param("userName") String username);
}