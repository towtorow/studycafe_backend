package com.studycafe.studycafe_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@SpringBootApplication
@EnableAspectJAutoProxy
public class StudyCafeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyCafeBackendApplication.class, args);
	}

}
