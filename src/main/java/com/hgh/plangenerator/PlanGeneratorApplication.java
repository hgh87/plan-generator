package com.hgh.plangenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PlanGeneratorApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PlanGeneratorApplication.class, args);
	}
}
