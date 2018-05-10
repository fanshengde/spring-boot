package com.fsd.config.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fsd.bean.PermissionInfo;
import com.fsd.bean.QPermissionInfo;
import com.fsd.bean.QRoleInfo;
import com.fsd.bean.QRolePermission;
import com.fsd.bean.QUserInfo;
import com.fsd.bean.QUserRole;
import com.fsd.bean.UserInfo;
import com.fsd.dao.PermissionInfoRepository;
import com.fsd.dao.UserInfoRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * Created by yangyibo on 17/2/7.
 */
@Service
public class UrlUserService implements UserDetailsService {
	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	PermissionInfoRepository permissionInfoRepository;

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	private JPAQueryFactory jpaQueryFactory;

	@PostConstruct
	public void init() {
		jpaQueryFactory = new JPAQueryFactory(entityManager);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) { // 重写loadUserByUsername 方法获得 userdetails 类型用户

		// UserInfo user = userInfoRepository.getByUserName(userName);
		UserInfo user = userInfoRepository.findByUsername(userName);
		if (user != null) {
			// List<PermissionInfo> permissions =
			// permissionInfoRepository.getByUserId(user.getSid());
			// QCity qCity = QCity.city;
			// QCity cityName = new QCity("name");
			// List<City> citys =
			// queryFactory.selectFrom(qCity).innerJoin(qCity).on(qCity.name.eq(cityName.name)).fetch();
			QRoleInfo roleInfo = QRoleInfo.roleInfo;
			QUserInfo userInfo = QUserInfo.userInfo;
			QUserRole userRole = QUserRole.userRole;
			QPermissionInfo permissionInfo = QPermissionInfo.permissionInfo;
			QRolePermission rolePermission = QRolePermission.rolePermission;

			List<PermissionInfo> permissions = jpaQueryFactory.select(permissionInfo).from(userInfo).leftJoin(userRole)
					.on(userInfo.sid.eq(userRole.userInfo.sid)).leftJoin(roleInfo)
					.on(userRole.roleInfo.sid.eq(roleInfo.sid)).leftJoin(rolePermission)
					.on(rolePermission.roleInfo.sid.eq(roleInfo.sid)).leftJoin(permissionInfo)
					.on(permissionInfo.sid.eq(rolePermission.permissionInfo.sid)).where(userInfo.sid.eq(user.getSid()))
					.fetch();

			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			for (PermissionInfo permission : permissions) {
				if (permission != null && permission.getCnname() != null) {
					GrantedAuthority grantedAuthority = new UrlGrantedAuthority(permission.getPermissionUrl(),
							permission.getMethod());
					grantedAuthorities.add(grantedAuthority);
				}
			}
			user.setGrantedAuthorities(grantedAuthorities);
			return user;
		} else {
			throw new UsernameNotFoundException("admin: " + userName + " do not exist!");
		}
	}
}
