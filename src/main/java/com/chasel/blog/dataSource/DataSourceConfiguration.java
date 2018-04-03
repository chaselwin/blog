package com.chasel.blog.dataSource;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
  public class DataSourceConfiguration {
 
     @Bean(name = "dataSource")
	@ConfigurationProperties(prefix = "dbcp.datasource")
     public DataSource dataSource()
     {
		return DataSourceBuilder.create().type(org.apache.commons.dbcp.BasicDataSource.class).build();
     }
 }