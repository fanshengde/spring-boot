package com.fsd.control.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsd.domain.SysUser;
import com.fsd.repository.SysUserRepository;

@RestController
public class UserControl {

	@Autowired
	private SysUserRepository sysUserRepository;
	
	@RequestMapping(value="/ctrLogin", method=RequestMethod.POST)
	public SysUser login(@ModelAttribute SysUser sysUser, Model model) {
		System.out.println("fanshengde - test");
		return sysUser;
	}
	
}
