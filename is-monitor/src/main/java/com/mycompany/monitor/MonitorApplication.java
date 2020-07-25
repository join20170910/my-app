package com.mycompany.monitor;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author john
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MonitorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonitorApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
