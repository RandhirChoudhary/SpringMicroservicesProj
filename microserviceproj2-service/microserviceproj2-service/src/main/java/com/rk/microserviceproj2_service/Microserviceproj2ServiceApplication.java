package com.rk.microserviceproj2_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Microserviceproj2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Microserviceproj2ServiceApplication.class, args);
	}

}
