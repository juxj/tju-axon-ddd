package com.example.demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.InterceptorChain;
import org.axonframework.messaging.MessageHandlerInterceptor;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AxonMessageHandler implements MessageHandlerInterceptor<CommandMessage<?>> {


    /**
     * 所有Command都会在此被拦截. 并捕获异常，更换异常类型后抛出
     *
     * @param unitOfWork       .
     * @param interceptorChain .
     * @return .
     * @throws Exception .
     */
    @Override
    public Object handle(UnitOfWork<? extends CommandMessage<?>> unitOfWork,
                         InterceptorChain interceptorChain) throws Exception {
        try {
            CommandMessage<?> message = unitOfWork.getMessage();
            log.info("2->AxonMessageHandler::所有Command都会在此被拦截");
            return interceptorChain.proceed();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
