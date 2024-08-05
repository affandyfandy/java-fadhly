package com.example.lecture16.assignment1_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Assignment11Application {

	public static void main(String[] args) {
		SpringApplication.run(Assignment11Application.class, args);
	}

}
