package com.example.demo.handler;

import com.example.demo.command.UpdateUserNameEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.interceptors.MessageHandlerInterceptor;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class UserEventHandler {

    @EventHandler
    void on(UpdateUserNameEvent e) {
        log.info("1");
    }

    @MessageHandlerInterceptor
    void intercept(Message<?> message) {
        log.info("hello, message interceptor");
    }

}
