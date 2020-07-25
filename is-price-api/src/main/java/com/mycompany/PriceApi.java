package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author john
 */
@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
public class PriceApi {
  public static void main(String[] args) {
      SpringApplication.run(PriceApi.class,args);
  }
}
