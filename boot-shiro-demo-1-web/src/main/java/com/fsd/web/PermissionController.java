package com.fsd.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fsd.entity.SysPermission;
import com.fsd.entity.repository.SysPermissionRepository;

@Controller
@RequestMapping("/permission")
public class PermissionController {
	public final static Logger LOG = LoggerFactory.getLogger(PermissionController.class);
	
	@Autowired
	SysPermissionRepository sysPermissionRepository;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@RequiresPermissions("userInfo:view")
	public String permissionInfo(Model model) {
		Iterable<SysPermission> sysPermissions = sysPermissionRepository.findAll();
		model.addAttribute("sysPermissions", sysPermissions);
		return "permissionInfo";
	}
}
