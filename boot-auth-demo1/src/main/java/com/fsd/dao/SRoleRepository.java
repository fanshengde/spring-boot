package com.fsd.dao;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsd.entity.SRole;
@Repository
@Table(name = "s_role")
@Qualifier("sRoleRepository")
public interface SRoleRepository extends JpaRepository<SRole, Integer>{
	
}
