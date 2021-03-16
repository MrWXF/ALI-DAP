package com.ali.wzq.service.impl;

import com.ali.wzq.service.ProviderDemoService;
import com.ali.wzq.utils.RedisUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Random;

/**
 * 类说明
 *
 * @ClassName: ProviderDemoServiceImpl
 * @Author wuzhiqing
 * @Version V1.0.0
 * @Data 2021/3/12  12:05
 */
@Service
@org.springframework.stereotype.Service
public class ProviderDemoServiceImpl implements ProviderDemoService
{

	@Autowired
	private RedisUtils redisUtils;

	@Value("${server.port}")
	private String port;

	@Override
	public String hello()
	{

		return "hello dubbo for port:" + port;
	}

	@Override
	public String redisTest() {
//		long wzq = redisUtils.setSet("111", "wzq");
		long timeOut = 3L;
		redisUtils.setValueExpire("wzq1", "wwww22w", timeOut);
//		if (wzq == 0){
//
//		}
		return "null";
	}
}
