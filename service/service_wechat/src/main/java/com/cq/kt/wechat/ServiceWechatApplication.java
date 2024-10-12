package com.cq.kt.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-03-9:28
 * Description:
 *
 * @ Author:两袖青蛇
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.cq.kt")
@MapperScan("com.cq.kt.wechat.mapper")
@ComponentScan(basePackages = "com.cq.kt")
public class ServiceWechatApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceWechatApplication.class, args);
    }
}