package com.example.bitcoinService;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

@EnableEurekaClient
@SpringBootApplication
public class BitcoinServiceApplication {

	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore","src/main/resources/bsTrusted.jks");
        System.setProperty("javax.net.ssl.trustStorePassword","password");
		
		
		System.setProperty("KEY_STORE_CLASSPATH", "src/main/resources/bs.jks");
		System.setProperty("KEY_STORE_CLASSPATH_TRUST", "src/main/resources/bsTrusted.jks");
		System.setProperty("KEY_STORE_PASSWORD", "password");
		System.setProperty("KEY_STORE_TRUST_PASSWORD", "password");
		System.setProperty("KEY_STORE_ALIAS", "bc");
		
		SpringApplication.run(BitcoinServiceApplication.class, args);
	}
	
	@Bean
	public DiscoveryClient.DiscoveryClientOptionalArgs discoveryClientOptionalArgs() throws NoSuchAlgorithmException {
		DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
		System.setProperty("javax.net.ssl.keyStore", "src/main/resources/bs.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/bsTrusted.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("bc");
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());
		return args;
	}

}
