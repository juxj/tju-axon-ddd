package com.example.demo.configuration;


import com.example.demo.command.TestEvent;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPPublisher;
import org.axonframework.messaging.SubscribableMessageSource;

import java.util.List;
import java.util.stream.Collectors;

public class SelectiveAmqpPublisher extends SpringAMQPPublisher {

    static boolean shouldSend(Class<?> pt) {
        return TestEvent.class.isAssignableFrom(pt);
    }

    public SelectiveAmqpPublisher(SubscribableMessageSource<EventMessage<?>> messageSource) {
        super(messageSource);
    }


    @Override
    protected void send(List<? extends EventMessage<?>> events) {
        List<? extends EventMessage<?>> list = events.stream().filter(e -> shouldSend(e.getPayloadType())).collect(Collectors.toList());
        super.send(list);
    }

}
