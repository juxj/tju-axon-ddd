package com.example.demo.configuration;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;

import java.util.List;
import java.util.function.BiFunction;

/**
 * event dispatch interceptor
 */
public class AxonMessageDispatcher implements MessageDispatchInterceptor<CommandMessage<?>> {

    @Override
    public CommandMessage<?> handle(CommandMessage<?> message) {
        return MessageDispatchInterceptor.super.handle(message);
    }

    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(List<? extends CommandMessage<?>> messages) {
        return (integer, commandMessage) -> commandMessage;
    }
}
