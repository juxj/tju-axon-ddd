package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AggregateCommandValidator implements IAggregateCommandValidator{

    @Override
    public void validate(Message<?> message) throws Exception{
        // log.info(message.getPayloadType().getName());
        throw new Exception("手动抛出异常，终止命令执行.") ;
    }
}
