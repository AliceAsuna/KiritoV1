package com.alice.springboot.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class TestController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test()
    {
        return "测试接口连接";
    }
}
