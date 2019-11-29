package com.example.kpService;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.shared.transport.jersey.EurekaJerseyClientImpl.EurekaJerseyClientBuilder;

//@Configuration
//@BootstrapConfiguration
public class SslConfiguration {

	/*@Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
             //   .requestFactory(() -> validateSSL())
                .build();
    }
	
	private HttpComponentsClientHttpRequestFactory validateSSL(){
		String location = "src/main/resources/kp.jks";
	    String pass = "password";
        SSLContext sslContext = null;
        try{
            sslContext = SSLContextBuilder
                    .create()
                    .loadTrustMaterial(ResourceUtils.getFile(location), pass.toCharArray())
                    .build();
        }catch (Exception e){

        }
        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,new LocalHostnameVerifier());	//mi nemamo sertifikat koji je odobren od neke CA ustanove i zato ne koristimo CertificateHostVerifier
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return requestFactory;
    }

    private class LocalHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return "localhost".equalsIgnoreCase(s) || "127.0.0.1".equals(s);
        }
    }*/
	
	/*@Bean
	  public DiscoveryClient.DiscoveryClientOptionalArgs getTrustStoredEurekaClient(SSLContext sslContext) {
	    DiscoveryClient.DiscoveryClientOptionalArgs args = new DiscoveryClient.DiscoveryClientOptionalArgs();
	    args.setSSLContext(sslContext);
	    /*System.setProperty("javax.net.ssl.keyStore", "src/main/resources/kp.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStore", "src/main/resources/kpTrusted.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		EurekaJerseyClientBuilder builder = new EurekaJerseyClientBuilder();
		builder.withClientName("kp");
		builder.withSystemSSLConfiguration();
		builder.withMaxTotalConnections(10);
		builder.withMaxConnectionsPerHost(10);
		args.setEurekaJerseyClient(builder.build());*/
	   
	   /* return args;
	  }
	
	@Bean
	  public SSLContext sslContext() throws Exception {
		String location = "src/main/resources/kp.jks";
	    String pass = "password";
	    return new SSLContextBuilder().loadTrustMaterial(ResourceUtils.getFile(location), pass.toCharArray()).build();
	  }
	
	 @Bean
	  public static BeanFactoryPostProcessor registerPostProcessor() {
	    return (ConfigurableListableBeanFactory beanFactory) -> {
	      BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
	      for (String beanDefinitionName : registry.getBeanDefinitionNames()) {
	        if (beanDefinitionName.equalsIgnoreCase("discoveryClientOptionalArgs")) {
	          BeanDefinition beanDefinition = registry.containsBeanDefinition(beanDefinitionName) ? registry.getBeanDefinition(beanDefinitionName) : null;
	          if (beanDefinition != null) {
	            if (registry.containsBeanDefinition(beanDefinitionName)) {
	              registry.removeBeanDefinition(beanDefinitionName);
	            }
	          }
	        }
	      }
	    };
	  }
*/
	  

}
