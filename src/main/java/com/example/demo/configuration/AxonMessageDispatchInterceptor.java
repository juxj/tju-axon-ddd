package com.example.demo.configuration;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;

import java.util.List;
import java.util.function.BiFunction;

/**
 * 拦截所有Command{@link CommandMessage}.或事件 {@link org.axonframework.eventhandling.EventMessage}
 * 
 * event dispatch interceptor
 */
@Slf4j
public class AxonMessageDispatchInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    @Override
    public CommandMessage<?> handle(CommandMessage<?> message) {
        log.info("1->::UserMessageDispatchInterceptor:: 拦截所有Command");
        return MessageDispatchInterceptor.super.handle(message);
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        return (integer, commandMessage) -> commandMessage;
    }
}
