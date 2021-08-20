package com.example.demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@Slf4j
@Component
public class AxonMessageHandler implements MessageHandlerInterceptor<CommandMessage<?>> {


    @Override
    public Object handle(UnitOfWork<? extends CommandMessage<?>> unitOfWork,
                         InterceptorChain interceptorChain) throws Exception {
        CommandMessage<?> message = unitOfWork.getMessage();
        log.info(message.getCommandName());
        return interceptorChain.proceed();
    }
}
