package com.cq.kt.vod.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Tool:IntelliJ IDEA
 * Date:2024-06-20-23:24
 * Description:
 *
 * @ Author:两袖青蛇
 */
@Configuration

public class VodConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
