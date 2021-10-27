package com.example.demo.service;

import org.axonframework.messaging.Message;

public interface IAggregateCommandValidator {

    void validate(Message<?> message) throws Exception;
}
