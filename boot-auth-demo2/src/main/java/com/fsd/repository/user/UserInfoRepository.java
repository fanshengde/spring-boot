package com.fsd.repository.user;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fsd.entity.user.UserInfo;
@Repository
@Table(name = "user_info")
@Qualifier("userInfoRepository")
public interface UserInfoRepository extends CrudRepository<UserInfo, Long>{
	@Query("select u from UserInfo u where u.email=?1 and u.password=?2")
	UserInfo login(String email, String password);
	
	UserInfo findByEmailAndPassword(String email, String password);
	
	UserInfo findUserByEmail(String email);
}
