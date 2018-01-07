package com.fsd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.fsd.security.CustomUserService;
import com.fsd.service.MyFilterSecurityInterceptor;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
	
    @Bean
    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(customUserService());
//        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder(){
//
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return MD5Util.encode((String)rawPassword);
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return encodedPassword.equals(MD5Util.encode((String)rawPassword));
//            }}); //user Details Service验证
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
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
                .logout().permitAll()
                ; //注销行为任意访问


    }



}

