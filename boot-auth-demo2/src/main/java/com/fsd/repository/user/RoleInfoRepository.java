package com.fsd.repository.user;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsd.entity.user.UserInfo;
@Repository
@Table(name = "role_info")
@Qualifier("roleInfoRepository")
public interface RoleInfoRepository extends CrudRepository<UserInfo, Long>{
}
