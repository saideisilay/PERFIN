package edu.iu.perfin.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy(proxyTargetClass=true)
@ComponentScan(basePackages = "edu.iu.perfin")
public class PerfinApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PerfinApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(PerfinApplication.class, args);
	}
	
}
