package com.fsd.dao;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fsd.entity.SUser;
@Repository
@Table(name = "s_user")
@Qualifier("sUserRepository")
public interface SUserRepository extends JpaRepository<SUser, Integer>{
	@Query("select u from SUser u where u.email=?1 and u.password=?2")
	SUser login(String email, String password);
	
	SUser findByEmailAndPassword(String email, String password);
	
	SUser findUserByEmail(String email);
	
}
