package com.fsd.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fsd.dao.PermissionDao;
import com.fsd.dao.UserDao;
import com.fsd.domain.Permission;
import com.fsd.domain.SysRole;
import com.fsd.domain.SysUser;
import com.fsd.service.MyGrantedAuthority;


@Service
public class CustomUserService implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    UserDao userDao;
    
    @Autowired
    PermissionDao permissionDao;
    
    @Override
    public UserDetails loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户

        SysUser user = userDao.findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException(username + " 用户名不存在");
        }else {
        	List<Permission> permissions  = permissionDao.findByAdminUserId(user.getId());
        	List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        	for(Permission permission : permissions) {
        		if(permission != null && permission.getName() != null) {
        			GrantedAuthority grantedAuthority = new MyGrantedAuthority(permission.getUrl(), permission.getMethod());
        			grantedAuthorities.add(grantedAuthority);
        		}
        	}
        	return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
        }
    }

}
