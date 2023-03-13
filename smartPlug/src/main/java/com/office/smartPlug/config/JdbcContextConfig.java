package com.office.smartPlug.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class JdbcContextConfig {
	
	@Bean
	public DataSource dataSource() {
		
		DataSource dataSource = new DataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.0.105:3306/smartPlugProject?serverTimezone=Asia/Seoul");
		dataSource.setUsername("user0003");
		dataSource.setPassword("user0003");
		
		return dataSource;
		
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		
		return jdbcTemplate;
		
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		
		DataSourceTransactionManager transactionManager = 
				new DataSourceTransactionManager();
		
		transactionManager.setDataSource(dataSource());
		
		return transactionManager;
		
	}

}
