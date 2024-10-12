package com.cq.kt.activity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-02-23:03
 * Description:
 *
 * @ Author:两袖青蛇
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com.cq.kt")
@MapperScan("com.cq.kt.activity.mapper")
@EnableFeignClients("com.cq.kt")
public class ServiceActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceActivityApplication.class, args);
    }
}
