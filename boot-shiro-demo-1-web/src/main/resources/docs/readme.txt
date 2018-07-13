过滤器存在问题。
	1：(已解决）
	filterChainDefinitionMap.put("/**", "authc");修改为filterChainDefinitionMap.put("/templates/**", "authc");
	显示正常，但是登陆异常
	
	
	2：（已解决）
	filterChainDefinitionMap.put("/**", "authc")不修改得话，显示异常，但是登陆正常
	
	
	3：账号密码错误提示



html页面权限颗粒话功能：
https://blog.csdn.net/qq_26562641/article/details/79298069

Vue + ElementUI 手撸后台管理网站基本框架(二)权限控制
https://blog.csdn.net/harsima/article/details/77949448

后台经验分享：如何做权限管理系统设计？
https://blog.csdn.net/k7Jz78GeJJ/article/details/78489155

element下载地址
	https://unpkg.com/element-ui@2.4.3/

vue + element ui 实例
	https://github.com/lin-xin/vue-manage-system
	http://blog.gdfengshuo.com/example/work/#/dashboard
	


<meta name="viewport" content="width=device-width, initial-scale=1"><meta>
		<!-- 
			width - viewport的宽度 height - viewport的高度
			initial-scale - 初始的缩放比例
			minimum-scale - 允许用户缩放到的最小比例
			maximum-scale - 允许用户缩放到的最大比例
			user-scalable - 用户是否可以手动缩放
		 -->	
bootstrap模板
	http://demo.cssmoban.com/cssthemes4/cpts_985_cdk/index.html#
	http://www.cssmoban.com/cssthemes/houtaimoban/
	
	后台管理
	http://demo.cssmoban.com/cssthemes5/cpts_1048_bot/tabels.html

element ui	
	https://unpkg.com/element-ui@2.4.3/lib/
	
	
@RequestMapping(value = "/shiro/deletePermissionById/{sid}", method = RequestMethod.GET)
	@ResponseBody
	public String deletePermissionById(@PathVariable Long sid) {
	
	
	
fastjson
	https://blog.csdn.net/seashouwang/article/details/80201685