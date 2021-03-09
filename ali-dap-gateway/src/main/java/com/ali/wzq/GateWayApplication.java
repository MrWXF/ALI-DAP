package com.ali.wzq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 类说明
 *
 * @ClassName: GateWayApplication
 * @Author wuzhiqing
 * @Version V1.0.0
 * @Data 2021/3/9  16:59
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableZuulProxy
public class GateWayApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(GateWayApplication.class, args);
	}
}
