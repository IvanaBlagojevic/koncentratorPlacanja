package configuration;

import java.io.InputStream;
import java.security.KeyStore;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomConfigServiceBootstrapConfiguration {

	@Autowired
    private Environment environment;

    @Bean
    public ConfigServicePropertySourceLocator configServicePropertySourceLocator() {
        ConfigClientProperties clientProperties = new ConfigClientProperties(this.environment);
        //clientProperties.setUri(new String[] { "https://localhost:8888" });
        clientProperties.setUri(new String[] { "https://localhost:8086/config-server" });
        ConfigServicePropertySourceLocator configServicePropertySourceLocator =  new ConfigServicePropertySourceLocator(clientProperties);
        configServicePropertySourceLocator.setRestTemplate(customRestTemplate());
        return configServicePropertySourceLocator;
    }

    public RestTemplate customRestTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        KeyStore keyStore;
        KeyStore trustStore;
        HttpComponentsClientHttpRequestFactory requestFactory = null;
        try {
            keyStore = KeyStore.getInstance("JKS");
            ClassPathResource classPathResource = new ClassPathResource("auth.jks");
            InputStream inputStream = classPathResource.getInputStream();
            keyStore.load(inputStream, "password".toCharArray());

            trustStore = KeyStore.getInstance("JKS");
            ClassPathResource classPathResourceTrust = new ClassPathResource("authTrusted.jks");
            InputStream trustInput = classPathResourceTrust.getInputStream();
            trustStore.load(trustInput, "password".toCharArray());

            javax.net.ssl.SSLContext sslContext = SSLContextBuilder.create()
                    .loadKeyMaterial(keyStore, "password".toCharArray())
                    .loadTrustMaterial(trustStore, null).setProtocol("TLS")
                    .build();
            SSLConnectionSocketFactory sslConnectionSocketFactory =
                    new SSLConnectionSocketFactory(sslContext);

            HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory)
                    .setMaxConnTotal(Integer.valueOf(200))
                    .setMaxConnPerRoute(Integer.valueOf(200))
                    .build();

            requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            requestFactory.setReadTimeout(Integer.valueOf(10000));
            requestFactory.setConnectTimeout(Integer.valueOf(10000));

            restTemplate.setRequestFactory(requestFactory);
        } catch (Exception exception) {
            System.out.println("Exception Occured while creating restTemplate "+exception);
            exception.printStackTrace();
        }
        return restTemplate;

    }
}
