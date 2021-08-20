package com.example.demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.interceptors.MessageHandlerInterceptor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestEventHandler {


    @MessageHandlerInterceptor
    void intercept(Message<?> message) {
        log.debug("hello, message interceptor");
    }

}
