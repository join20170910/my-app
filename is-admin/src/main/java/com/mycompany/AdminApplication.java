package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author john
 */
@SpringBootApplication
@EnableZuulProxy
public class AdminApplication {
  public static void main(String[] args) {
    SpringApplication.run(AdminApplication.class,args);
  }
}
