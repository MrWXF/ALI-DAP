package com.ali.wzq.controller;

import com.ali.wzq.service.ProviderDemoService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消费者的demo控制层
 *
 * @Package com.ali.wzq.controller
 * @Author wuzhiqing
 * @className ALI-DAP
 * @Version v1.0.0
 * @createData 2021/3/14  16:13
 */
@RestController
@RequestMapping(value = "/v1/demo")
public class DemoController {

	@Autowired
	private ProviderDemoService providerDemoService;

	@GetMapping("/hello")
	public String hello(){

		return "1111";
	}

	@GetMapping("/redisTest")
	@SentinelResource("redisTest")
	public String redisTest () {
		String s = providerDemoService.redisTest();
		return s;
	}

}
