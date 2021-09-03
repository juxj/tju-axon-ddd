package com.example.demo.controller;

import com.example.demo.service.ITestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/")
public class TestController {

    @Resource
    private ITestService testService;

    @GetMapping("/")
    public String hello() {
        return testService.sayHello("Jack");
    }


    @GetMapping("/name")
    public String name() throws Exception {
        return testService.getName();
    }
}
