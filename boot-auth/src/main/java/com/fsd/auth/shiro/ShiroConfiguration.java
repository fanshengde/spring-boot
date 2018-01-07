package com.fsd.auth.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fsd.auth.service.StudentService;
import com.fsd.auth.shiro.security.MyShiroRealm;
import com.fsd.dao.IscoreDao;

@Configuration
public class ShiroConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);
	
	@Bean
	public EhCacheManager  getEhChacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}
	
	@Bean(name = "myShiroRealm")
	public MyShiroRealm myShiroRealm(EhCacheManager cacheManager) {
		MyShiroRealm realm = new MyShiroRealm();
		realm.setCacheManager(cacheManager);
		return realm;
		
	}
	
	@Bean(name="lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
		daap.setProxyTargetClass(true);
		return daap;
	}
	
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(MyShiroRealm myShiroRealm) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(myShiroRealm);
		
		dwsm.setCacheManager(getEhChacheManager());
		
		return dwsm;
	}
	
	@Bean
	public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager  securityManager) {
		AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
		aasa.setSecurityManager(securityManager);
		return aasa;
	}
	
	
	private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean, StudentService stuService, IscoreDao scoreDao) {
		Map<String, String > filterChainDefinitionMap = new LinkedHashMap<String, String>();
		filterChainDefinitionMap.put("/user", "authc");
		logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");
		filterChainDefinitionMap.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取

        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/**", "anon");//anon 可以理解为不拦截
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
	}
	
	
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager secutityManager, StudentService stuService, IscoreDao scoreDao) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new MShiroFilterFactoryBean();
		
		shiroFilterFactoryBean.setSecurityManager(secutityManager);
		shiroFilterFactoryBean.setLoginUrl("/login");
		
		shiroFilterFactoryBean.setSuccessUrl("/user");
		
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		
		loadShiroFilterChain(shiroFilterFactoryBean, stuService, scoreDao);
		
		return shiroFilterFactoryBean;
	}
}
