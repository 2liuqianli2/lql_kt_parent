package com.cq.kt.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-02-18:43
 * Description:
 *
 * @ Author:两袖青蛇
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cq.kt.order.mapper")
@ComponentScan("com.cq.kt")
public class ServiceOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}
