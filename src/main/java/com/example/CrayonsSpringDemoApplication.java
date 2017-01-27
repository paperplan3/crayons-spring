package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class CrayonsSpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrayonsSpringDemoApplication.class, args);
	}

}

