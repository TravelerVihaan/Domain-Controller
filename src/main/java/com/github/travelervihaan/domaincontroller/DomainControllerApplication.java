package com.github.travelervihaan.domaincontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DomainControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DomainControllerApplication.class, args);
		
	}
}
