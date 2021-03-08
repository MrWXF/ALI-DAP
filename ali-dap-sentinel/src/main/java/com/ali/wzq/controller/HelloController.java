package com.ali.wzq.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类说明
 *
 * @Package com.ali.wzq.controller
 * @Author wuzhiqing
 * @className ALI-DAP
 * @Version v1.0.0
 * @createData 2021/3/6  13:30
 */
@RestController
@RequestMapping(value = "/v1")
public class HelloController {

    @GetMapping(value = "/hello")
    @SentinelResource("hello")
    public String hello () {

        return "hello Sentinel";
    }
}
