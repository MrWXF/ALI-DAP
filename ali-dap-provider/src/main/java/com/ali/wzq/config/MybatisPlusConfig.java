package com.ali.wzq.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 类说明
 *
 * @ClassName: MybatisPlusConfig
 * @Author wuzhiqing
 * @Version V1.0.0
 * @Data 2021/3/16  18:48
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.ali.wzq.entity")
public class MybatisPlusConfig
{
	@Bean
	public PaginationInterceptor paginationInterceptor(){
		PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
		return paginationInterceptor;
	}
}
