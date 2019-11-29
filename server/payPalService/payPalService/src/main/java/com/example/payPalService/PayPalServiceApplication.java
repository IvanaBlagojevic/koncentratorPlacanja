package com.example.payPalService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayPalServiceApplication {

	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore","src/main/resources/ppsTrusted.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","password");
		
		System.setProperty("KEY_STORE_CLASSPATH", "src/main/resources/pps.jks");
		System.setProperty("KEY_STORE_CLASSPATH_TRUST", "src/main/resources/ppsTrusted.jks");
		System.setProperty("KEY_STORE_PASSWORD", "password");
		System.setProperty("KEY_STORE_TRUST_PASSWORD", "password");
		System.setProperty("KEY_STORE_ALIAS", "pps");
		SpringApplication.run(PayPalServiceApplication.class, args);
	
	}

}
