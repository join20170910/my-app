package com.mycompany.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author john
 */
@SpringBootApplication
@EnableResourceServer
public class PriceApi {
  public static void main(String[] args) {
      SpringApplication.run(PriceApi.class,args);
  }
}
