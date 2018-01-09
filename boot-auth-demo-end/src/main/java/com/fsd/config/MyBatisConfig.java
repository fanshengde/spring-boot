package com.fsd.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
	@Autowired
	private DataSource dataSource;

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext applicationContext) throws IOException {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		// use log4j log
		configuration.setLogImpl(org.apache.ibatis.logging.log4j.Log4jImpl.class);

		sessionFactory.setConfiguration(configuration);

		sessionFactory.setMapperLocations(applicationContext.getResources("classpath:com/fsd/mapper/*.xml"));
		return sessionFactory;
	}
}
