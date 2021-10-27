package com.example.demo.handler;

import com.example.demo.cqe.event.UserNameUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.interceptors.MessageHandlerInterceptor;
import org.springframework.stereotype.Component;


/**
 * 写数据库
 */

@Slf4j
@Component
public class UserEventHandler {

    @EventHandler
    void on(UserNameUpdatedEvent e) {
        log.info("UserNameUpdatedEvent");
    }

    @MessageHandlerInterceptor
    void intercept(Message<?> message) {
        log.info("MessageHandlerInterceptor，标签");
    }

}
