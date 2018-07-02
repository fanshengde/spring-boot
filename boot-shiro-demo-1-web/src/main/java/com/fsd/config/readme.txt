过滤器存在问题。
	1：
	filterChainDefinitionMap.put("/**", "authc");修改为filterChainDefinitionMap.put("/templates/**", "authc");
	显示正常，但是登陆异常
	
	
	2：
	filterChainDefinitionMap.put("/**", "authc")不修改得话，显示异常，但是登陆正常