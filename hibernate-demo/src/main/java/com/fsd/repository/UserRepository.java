package com.fsd.repository;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsd.pojo.UserInfo;



@Repository
@Table(name = "t_user")
@Qualifier("userRepository")
public interface UserRepository extends CrudRepository<UserInfo, Long>{
	public UserInfo findOne(Long sid);
	
	@Query("select t from UserInfo t where t.username=:username")
	public UserInfo findUserByName(@Param("username") String username);
}
