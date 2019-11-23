package com.example.kpService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class KpServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KpServiceApplication.class, args);
	}

}
