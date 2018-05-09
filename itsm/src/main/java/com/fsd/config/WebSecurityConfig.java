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
	        http.authorizeRequests()
				.antMatchers("/css/**", "/js/**","/images/**", "/webjars/**", "**/favicon.ico", "/index")
				.permitAll()
		        .anyRequest().authenticated() //任何请求,登录后可以访问(任何尚未匹配的URL只需要验证用户即可访问)
		        .and()
		        .formLogin()
		        .loginPage("/login")
		        .failureUrl("/login?error")
		        .permitAll() //登录页面用户任意访问
		        .and()
		        .logout().permitAll(); //注销行为任意访问
	        http.addFilterBefore(urlFilterSecurityInterceptor, FilterSecurityInterceptor.class).csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
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
