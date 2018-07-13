package com.fsd.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fsd.entity.SysRole;
import com.fsd.entity.repository.SysRoleRepository;

@Controller
@RequestMapping("/role")
public class RoleController {
	public final static Logger LOG = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	SysRoleRepository sysRoleRepository;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@RequiresPermissions("userInfo:view")
	public String roleInfo(Model model) {
		Iterable<SysRole> sysRoles = sysRoleRepository.findAll();
		model.addAttribute("sysRoles", sysRoles);
		return "roleInfo";
	}
	
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	@RequiresPermissions("userInfo:add")
	public String addRoleIndex(@ModelAttribute(value="sysRole") SysRole sysRole, Model model) {
		model.addAttribute("sysRole", sysRole);
		return "roleInfoAdd";
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@RequiresPermissions("userInfo:add")
	public String addRole(@ModelAttribute(value="sysRole") SysRole sysRole, Model model) {
		sysRoleRepository.save(sysRole);
		return "roleInfoAdd";
	}
}
