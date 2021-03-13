package com.ali.wzq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 类说明
 *
 * @ClassName: AliDapProviderApplication
 * @Author wuzhiqing
 * @Version V1.0.0
 * @Data 2021/3/12  10:45
 */

@SpringBootApplication(scanBasePackages = "com.ali.wzq")
@EnableDiscoveryClient
public class AliDapProviderApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(AliDapProviderApplication.class, args);
	}
}
