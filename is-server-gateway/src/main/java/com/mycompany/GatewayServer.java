package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/** 网关启动类
 * @author john
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayServer {
  public static void main(String[] args) {
      SpringApplication.run(GatewayServer.class,args);
  }
}
