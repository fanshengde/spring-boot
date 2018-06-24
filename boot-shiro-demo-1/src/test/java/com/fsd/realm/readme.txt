Shiro 默认提供的 Realm结构
Realm
	--> CachingRealm
		--> AuthenticatingRealm
			--> AuthorizingRealm
				--> JDBCRealm   通过 sql 查询相应的信息，如 “select password from users where username = ?” 获取用户密码，“select password, password_salt from users where username = ?” 获取用户密码及盐；“select role_name from user_roles where username = ?” 获取用户角色；“select permission from roles_permissions where role_name = ?” 获取角色对应的权限信息；也可以调用相应的 api 进行自定义 sql；
				--> JndiLdapReale
				--> AbstractLdapRealm
					--> ActiveDirectoryRealm
				--> SimpleAccountRealm
					--> TextConfigurationRealm
						--> IniRealm  部分指定用户名 / 密码及其角色；[roles] 部分指定角色即权限信息；
						--> PropertiesRealm  user.username=password,role1,role2 指定用户名 / 密码及其角色；role.role1=permission1,permission2 指定角色及权限信息；
						
Shiro 授权						
	授权
		授权，也叫访问控制，即在应用中控制谁能访问哪些资源（如访问页面/编辑数据/页面操作等）。
		在授权中需了解的几个关键对象：主体（Subject）、资源（Resource）、权限（Permission）、角色（Role）。
	
	主体
		主体，即访问应用的用户，在 Shiro 中使用 Subject 代表该用户。用户只有授权后才允许访问相应的资源。
	
	资源
		在应用中用户可以访问的任何东西，
		比如访问 JSP 页面、查看/编辑某些数据、访问某个业务方法、打印文本等等都是资源。
		用户只要授权后才能访问。
	
	权限
		安全策略中的原子授权单位，通过权限我们可以表示在应用中用户有没有操作某个资源的权力。
		即权限表示在应用中用户能不能访问某个资源，如： 访问用户列表页面
		查看/新增/修改/删除用户数据（即很多时候都是 CRUD（增查改删）式权限控制）打印文档等等。。。
	
		如上可以看出，权限代表了用户有没有操作某个资源的权利，即反映在某个资源上的操作允不允许，不反映谁去执行这个操作。
		所以后续还需要把权限赋予给用户，即定义哪个用户允许在某个资源上做什么操作（权限），Shiro 不会去做这件事情，而是由实现人员提供。
	
		Shiro 支持粗粒度权限（如用户模块的所有权限）和细粒度权限（操作某个用户的权限，即实例级别的），后续部分介绍。
	
	角色
		角色代表了操作集合，可以理解为权限的集合，一般情况下我们会赋予用户角色而不是权限，
		即这样用户可以拥有一组权限，赋予权限时比较方便。
		典型的如：项目经理、技术总监、CTO、开发工程师等都是角色，不同的角色拥有一组不同的权限。
	
		隐式角色：
			即直接通过角色来验证用户有没有操作权限，
			如在应用中 CTO、技术总监、开发工程师可以使用打印机，
			假设某天不允许开发工程师使用打印机，此时需要从应用中删除相应代码；
			再如在应用中 CTO、技术总监可以查看用户、查看权限；突然有一天不允许技术总监查看用户、查看权限了，
			需要在相关代码中把技术总监角色从判断逻辑中删除掉；即粒度是以角色为单位进行访问控制的，粒度较粗；
			如果进行修改可能造成多处代码修改。
	
		显示角色：
			在程序中通过权限控制谁能访问某个资源，角色聚合一组权限集合；
			这样假设哪个角色不能访问某个资源，只需要从角色代表的权限集合中移除即可；
			无须修改多处代码；即粒度是以资源/实例为单位的；粒度较细。
	
	权限管理的分类
		RBAC -- 基于角色的访问控制
		RBAC新解 -- 基于资源的访问控制
		https://blog.csdn.net/youni808/article/details/25612271
	
	 Shiro ini配置：	
		#单个资源多个权限  格式：role42="system:user:update,delete"  表示角色4拥有 system:user 资源的 update 和 delete 权限
		#单个资源全部权限 role51="system:user:create,update,delete,view" 或者 role52=system:user:*（可以简写：role53=system:user 推荐使用上边的）
		#所有资源全部权限 role61=*:view
		#单个实例单个权限 role71=user:view:1
		#单个实例所有权限 role73=user:*:1
		#所有实例单个权限 role74=user:auth:*	
		
		

shrio拦截器
	Filter
		AbstractFilter
			NameableFilter //给 Filter 起个名字，如果没有设置默认就是 FilterName
				OncePerRequestFilter //用于防止多次执行 Filter 的；也就是说一次请求只会走一次拦截器链；另外提供 enabled 属性，
									      表示是否开启该拦截器实例，默认 enabled=true 表示开启，如果不想让某个拦截器工作，可以设置为 false 即可。
					AdviceFilter // AdviceFilter 提供了 AOP 风格的支持，类似于 SpringMVC 中的 Interceptor： 方法宝包括（preHandler()，postHandle()，afterCompletion()）
						PathMatchingFilter
							AccessControlFilter
					AbstactShiroFilter
						ShiroFilter //整个 Shiro 的入口点，用于拦截需要安全控制的请求进行处理，这个之前已经用过了。		
						
						
	AdviceFilter方法介绍：
	    preHandler：类似于 AOP 中的前置增强；在拦截器链执行之前执行；如果返回 true 则继续拦截器链；否则中断后续的拦截器链的执行直接返回；进行预处理（如基于表单的身份验证、授权）
	    postHandle：类似于 AOP 中的后置返回增强；在拦截器链执行完成后执行；进行后处理（如记录执行时间之类的）；
	    afterCompletion：类似于 AOP 中的后置最终增强；即不管有没有异常都会执行；可以进行清理资源（如接触 Subject 与线程的绑定之类的）；
		