package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author john
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients

public class Oauth2AuthServer {
  public static void main(String[] args) {
      SpringApplication.run(Oauth2AuthServer.class,args);
  }
}
