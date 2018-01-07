package com.fsd.site.datasource.mysql;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@EnableTransactionManagement
@Configuration
//@PropertySource(value = "{classpath:application.properties}")
@PropertySource("classpath:config/config.properties")
public class MySqlBeanConfig {
	@Autowired
	private Environment env;
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(env.getProperty("source.driverClassName").trim());
		dataSource.setUrl(env.getProperty("source.url").trim());
		dataSource.setUsername(env.getProperty("source.username").trim());
		dataSource.setPassword(env.getProperty("source.password").trim());
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}

}
