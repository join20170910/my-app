package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author john
 */
@Configuration
@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
public class OrderApi {
  public static void main(String[] args) {
      SpringApplication.run(OrderApi.class,args);
  }

  @Bean
  OAuth2RestTemplate getOAuth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context){
      return new OAuth2RestTemplate(resource, context);
  }

}
