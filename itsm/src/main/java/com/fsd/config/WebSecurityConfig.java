package com.fsd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.fsd.config.security.UrlFilterSecurityInterceptor;
import com.fsd.config.security.UrlUserService;
import com.fsd.utils.MD5Util;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UrlUserService urlUserService;

	@Autowired
	SessionRegistry sessionRegistry;

	@Autowired
	private UrlFilterSecurityInterceptor urlFilterSecurityInterceptor;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.csrf()
//			.disable()
//			.authorizeRequests()
//			.antMatchers("/login")
//			.permitAll()
//			.antMatchers("/logout")
//			.permitAll()
//			.antMatchers("/images/**")
//			.permitAll()
//			.antMatchers("/js/**").permitAll().antMatchers("/css/**")
//				.permitAll().antMatchers("fonts/**").permitAll().antMatchers("/").permitAll().anyRequest()
//				.authenticated().and().sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry).and()
//				.and().logout().invalidateHttpSession(true).clearAuthentication(true).and().httpBasic();
	        http.authorizeRequests()  //http.authorizeRequests()方法有多个子节点，每个macher按照他们的声明顺序执行。
				.antMatchers("/css/**", "/js/**","/images/**", "/webjars/**", "**/favicon.ico", "/index") //我们指定任何用户都可以通过访问的多个URL模式。任何用户都可以访问URL以"/resources/" "/images", 或者 "/index"开头的URL。
				.permitAll()
				//.antMatchers("admin/**").hasRole("ADMIN") //以 "/admin/" 开头的URL只能由拥有 "ROLE_ADMIN"角色的用户访问。请注意我们使用 hasRole 方法，没有使用 "ROLE_" 前缀.
				//.antMatchers("/db/**").access("hasRole('ADMIN' and hasRole('DBA')") // 	任何以"/db/" 开头的URL需要用户同时具有 "ROLE_ADMIN" 和 "ROLE_DBA"。和上面一样我们的 hasRole 方法也没有使用 "ROLE_" 前缀.
		        .anyRequest().authenticated() //任何请求,登录后可以访问(任何尚未匹配的URL只需要验证用户即可访问)  尚未匹配的任何URL要求用户进行身份验证
		        .and()
		        .formLogin()
		        .loginPage("/login") //指定登陆页路径 如本例中在指定路径为resources/templates/login.html
		        .failureUrl("/login?error")
		        .permitAll() //登录页面用户任意访问
		        .and()
		        .logout().permitAll(); //注销行为任意访问
	        http.addFilterBefore(urlFilterSecurityInterceptor, FilterSecurityInterceptor.class).csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { //配置正常的验证。
		auth.userDetailsService(urlUserService).passwordEncoder(new PasswordEncoder() {

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return encodedPassword.equals(MD5Util.encode((String) rawPassword));
			}

			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Util.encode((String) rawPassword);
			}
		});
	}

	@Bean
	public SessionRegistry getSessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

}
