package com.example.demo.configuration;


import com.example.demo.cqe.event.UserNameUpdatedEvent;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPPublisher;
import org.axonframework.messaging.SubscribableMessageSource;

import java.util.List;
import java.util.stream.Collectors;

public class SelectiveAmqpPublisher extends SpringAMQPPublisher {

    /**
     * 定义哪些事件需要被RabbitMQ转发, 后续的框架中定义了一个列表
     * @param pt .
     * @return .
     */
    static boolean shouldSend(Class<?> pt) {
        return UserNameUpdatedEvent.class.isAssignableFrom(pt);
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
