package edu.iu.perfin.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Veritabani baglantilarini saglayacak olan 
 * havuz.
 * 
 * */
@Configuration
public class PoolConfig {

	@Value("${spring.datasource.username}")
	private String user;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	@Value("${spring.datasource.dataSourceClassName}")
	private String dataSourceClassName;

	@Value("${spring.datasource.connectionTimeout}")
	private int connectionTimeout;

	@Value("${spring.datasource.maxLifetime:180000}")
	private int maxLifetime;
	
	@Value("${spring.datasource.schema}")
	private String schema;
	
	@Value("${spring.datasource.maxPoolSize:2}")
	private Integer maxPoolSize;

	@Bean
	public DataSource primaryDataSource() {
		Properties dsProps = new Properties();
		dsProps.put("url", dataSourceUrl);
		dsProps.put("user", user);
		dsProps.put("password", password);

		Properties cProps = new Properties();
		cProps.put("connectionTestQuery", "select 1 from dual");
		cProps.put("connectionTimeout", connectionTimeout);
		cProps.put("dataSourceClassName", dataSourceClassName);
		cProps.put("dataSourceProperties", dsProps);
		cProps.put("maxLifetime", maxLifetime);
		

		// A default max pool size of 10 seems reasonable for now, so no need to configure for now.
		HikariConfig hc = new HikariConfig(cProps);
		
		hc.setConnectionTestQuery("select 1 from dual");
		hc.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");
		hc.setMaximumPoolSize(maxPoolSize);
		
		if(StringUtils.isBlank(schema)) {
			Properties conPr = new Properties();
			conPr.put("schema", schema);
			dsProps.put("connectionProperties", conPr);
		}
		
		HikariDataSource ds = new HikariDataSource(hc);
		return ds;
	}
}
