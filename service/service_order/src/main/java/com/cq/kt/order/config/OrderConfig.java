package com.cq.kt.order.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-07-02-18:44
 * Description:
 *
 * @ Author:两袖青蛇
 */
@Configuration
public class OrderConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
