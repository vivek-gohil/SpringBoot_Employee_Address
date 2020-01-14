package com.mindgate.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.mindgate")
@SpringBootApplication
public class EmployeeApplication {

	public static void main(String[] args) {
		System.out.println("Start");
		SpringApplication.run(EmployeeApplication.class, args);
		System.out.println("End");
	}

}
