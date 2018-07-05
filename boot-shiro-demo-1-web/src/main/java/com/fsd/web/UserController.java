package com.fsd.web;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.SimpleByteSource;
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
	public String userInfoIndex(@ModelAttribute(value="sysUser") SysUser sysUser, Model model) {
		model.addAttribute("sysUser", sysUser);
		return "userInfoAdd";
	}
	
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	@RequiresPermissions("userInfo:add") // 权限管理;
	public String userInfoAdd(@ModelAttribute(value="sysUser") SysUser sysUser, Model model) {
		String salt =new SecureRandomNumberGenerator().nextBytes().toHex();
		SimpleHash pw = new SimpleHash("md5", sysUser.getPassword(), sysUser.getUsername()+salt, 2);
		
		sysUser.setPassword(pw.toString());
		sysUser.setSalt(salt);
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