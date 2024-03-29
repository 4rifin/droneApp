package com.springjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class SpringSchedulerDroneApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSchedulerDroneApplication.class, args);
	}
}
