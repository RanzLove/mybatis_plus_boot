package com.ranz.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @program: mybatis-plus
 * @description:
 * @author: J.Flying
 * @create: 2020-10-10 16:08
 */
@MapperScan("com.ranz.mapper")
@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfig {

    /** 
    * @Description: 乐观锁配置
    * @Author: J.Flying 
    * @Date: 2020/10/10 
    */ 
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * @Description: 配置分页
     * @Author: J.Flying
     * @Date: 2020/10/10
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
