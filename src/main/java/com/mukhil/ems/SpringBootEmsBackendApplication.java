package com.mukhil.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages="com.mukhil.ems.controller")
@EntityScan(basePackages="com.mukhil.ems.model")
@EnableJpaRepositories(basePackages="com.mukhil.ems.dao")
public class SpringBootEmsBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootEmsBackendApplication.class, args);
	}

}
