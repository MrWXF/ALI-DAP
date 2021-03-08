package com.ali.wzq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 类说明
 *
 * @Package com.ali.wzq
 * @Author wuzhiqing
 * @className ALI-DAP
 * @Version v1.0.0
 * @createData 2021/3/6  10:38
 */

@SpringBootApplication(scanBasePackages = "com.ali.wzq")
@EnableDiscoveryClient
public class NacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }
}
