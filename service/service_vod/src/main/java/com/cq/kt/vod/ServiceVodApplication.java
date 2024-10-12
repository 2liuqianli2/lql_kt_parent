package com.cq.kt.vod;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-06-20-23:17
 * Description:
 *
 * @ Author:两袖青蛇
 */
@SpringBootApplication
@MapperScan("com.cq.kt.vod.mapper")
@ComponentScan("com.cq.kt")
@EnableDiscoveryClient
public class ServiceVodApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVodApplication.class,args);
    }
}
