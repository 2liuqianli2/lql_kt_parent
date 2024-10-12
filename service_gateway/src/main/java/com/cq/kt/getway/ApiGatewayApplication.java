package com.cq.kt.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-02-15:03
 * Description:
 *
 * @ Author:两袖青蛇
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class,args);
    }
}
