package com.ali.wzq.service.impl;

import com.ali.wzq.service.ProviderDemoService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * 类说明
 *
 * @ClassName: ProviderDemoServiceImpl
 * @Author wuzhiqing
 * @Version V1.0.0
 * @Data 2021/3/12  12:05
 */
@Service
public class ProviderDemoServiceImpl implements ProviderDemoService
{
	@Value("${server.port}")
	private String port;

	@Override
	public String hello()
	{

		return "hello dubbo for port:" + port;
	}
}
