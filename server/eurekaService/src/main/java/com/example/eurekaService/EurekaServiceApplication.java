package com.example.eurekaService;


import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServiceApplication {
	

	public static void main(String[] args) {
		System.setProperty("KEY_STORE_CLASSPATH", "src/main/resources/eureka.jks");
		System.setProperty("KEY_STORE_CLASSPATH_TRUST", "src/main/resources/eurekaTrusted.jks");
		System.setProperty("KEY_STORE_PASSWORD", "password");
		System.setProperty("KEY_STORE_TRUST_PASSWORD", "password");
		System.setProperty("KEY_STORE_ALIAS", "eureka");
		
		SpringApplication.run(EurekaServiceApplication.class, args);
		System.out.print("EurekaService start");
		
	}

}
