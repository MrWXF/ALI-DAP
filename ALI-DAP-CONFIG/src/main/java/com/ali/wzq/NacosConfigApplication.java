package com.ali.wzq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 类说明
 *
 * @ClassName: NacosConfigApplication
 * @Author wuzhiqing
 * @Version V1.0.0
 * @Data 2021/3/4  14:50
 */
@SpringBootApplication
public class NacosConfigApplication
{
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(NacosConfigApplication.class, args);
		String userName = applicationContext.getEnvironment().getProperty("user.name");
		String userAge = applicationContext.getEnvironment().getProperty("user.age");
		System.err.println("user name :" +userName+"; age: "+userAge);
	}
}
