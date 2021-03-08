package com.ali.wzq.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 类说明
 *
 * @Package com.ali.wzq.config
 * @Author wuzhiqing
 * @className ALI-DAP
 * @Version v1.0.0
 * @createData 2021/3/6  10:51
 */

@SpringBootConfiguration
public class NacosRestTemplate {

    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }
}
