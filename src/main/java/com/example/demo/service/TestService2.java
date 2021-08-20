package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class TestService2 implements ITestService2{
    @Override
    public String getHello() {
        return "Hello 2";
    }
}
