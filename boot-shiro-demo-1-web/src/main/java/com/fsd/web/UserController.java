package com.fsd.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fsd.entity.SysUser;
import com.fsd.entity.repository.SysUserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	SysUserRepository sysUserRepository;
	/**
	 * 用户查询.
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	@RequiresPermissions("userInfo:view") // 权限管理;
	public String userInfo(Model model) {
		Iterable<SysUser> sysUsers = sysUserRepository.findAll();
		
		model.addAttribute("sysUsers", sysUsers);
		
		return "userInfo";
	}

	/**
	 * 用户添加;
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userAdd", method = RequestMethod.GET)
	@RequiresPermissions("userInfo:add") // 权限管理;
	public String userInfoAdd(@ModelAttribute(value="sysUser") SysUser sysUser, Model model) {
		model.addAttribute("sysUser", sysUser);
		return "userInfoAdd";
	}
	
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	@RequiresPermissions("userInfo:add") // 权限管理;
	public String userInfoAdd(@ModelAttribute SysUser sysUser) {
		sysUserRepository.save(sysUser);
		return "userInfoAdd";
	}

	/**
	 * 用户删除;
	 * 
	 * @return
	 */
	@RequestMapping("/userDel")
	@RequiresPermissions("userInfo:del") // 权限管理;
	public String userDel() {
		return "userInfoDel";
	}
}
