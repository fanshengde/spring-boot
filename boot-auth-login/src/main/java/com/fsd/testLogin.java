package com.fsd;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;

//


public class testLogin {
	Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
	
	SecurityManager secutityManager = factory.getInstance();
	
//	SecurityUtils.setSecutityManager(secutityManager);
}
