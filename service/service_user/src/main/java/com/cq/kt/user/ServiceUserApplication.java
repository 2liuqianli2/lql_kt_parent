package com.cq.kt.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-02-23:13
 * Description:
 *
 * @ Author:两袖青蛇
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cq.kt")
@ComponentScan("com.cq.kt")
public class ServiceUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }

}
