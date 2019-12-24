package com.example.bankAcquirer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankAcquirerApplication {

	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore","src/main/resources/bankAcquirerTrusted.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","password");

		System.setProperty("KEY_STORE_CLASSPATH", "src/main/resources/bankAcquirer.jks");
		System.setProperty("KEY_STORE_CLASSPATH_TRUST", "src/main/resources/bankAcquirerTrusted.jks");
		System.setProperty("KEY_STORE_PASSWORD", "password");
		System.setProperty("KEY_STORE_TRUST_PASSWORD", "password");
		System.setProperty("KEY_STORE_ALIAS", "bankacquirer");
		SpringApplication.run(BankAcquirerApplication.class, args);
	}

}
