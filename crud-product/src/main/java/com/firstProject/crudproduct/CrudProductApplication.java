package com.firstProject.crudproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class CrudProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudProductApplication.class, args);
	}

}
