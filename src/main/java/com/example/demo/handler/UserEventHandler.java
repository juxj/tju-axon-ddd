package com.example.demo.handler;

import com.example.demo.cqe.event.UserCreateEvent;
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

    @EventHandler
    void on(UserCreateEvent e) {
        log.info("UserCreateEvent");
    }


    @MessageHandlerInterceptor
    void intercept(Message<?> message) {
        log.info("3->MessageHandlerInterceptor, {}", message.getPayload().getClass().getName());
    }

}
