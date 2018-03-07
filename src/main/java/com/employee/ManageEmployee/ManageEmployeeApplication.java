package com.employee.ManageEmployee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.employee.config.SecurityConfig;
import com.employee.controller.ControllerComponentScan;
import com.employee.repository.RepositoryComponentScan;

@SpringBootApplication(scanBasePackageClasses= {SecurityConfig.class,RepositoryComponentScan.class,ControllerComponentScan.class})
public class ManageEmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageEmployeeApplication.class, args);
	}
}
