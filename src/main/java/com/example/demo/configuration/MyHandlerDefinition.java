package com.example.demo.configuration;

import org.axonframework.messaging.annotation.HandlerDefinition;
import org.axonframework.messaging.annotation.MessageHandlingMember;
import org.axonframework.messaging.annotation.ParameterResolverFactory;

import java.lang.reflect.Executable;
import java.util.Optional;

public class MyHandlerDefinition implements HandlerDefinition {
    @Override
    public <T> Optional<MessageHandlingMember<T>> createHandler(Class<T> declaringType,
                                                                Executable executable,
                                                                ParameterResolverFactory parameterResolverFactory) {
        return Optional.empty();
    }
}
