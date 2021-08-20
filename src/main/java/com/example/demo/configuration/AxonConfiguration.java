package com.example.demo.configuration;

import com.example.demo.aggregate.TestAggregate;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.MethodCommandHandlerDefinition;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.annotation.HandlerDefinition;
import org.axonframework.messaging.annotation.HandlerEnhancerDefinition;
import org.axonframework.modelling.command.inspection.MethodCommandHandlerInterceptorDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class AxonConfiguration {


    @Resource
    private CommandBus commandBus;

    @Resource
    private EventStore eventStore;

    // @Bean
    // public HandlerEnhancerDefinition myCustomHandlerEnhancerDefinition() {
    //     return new MyMethodCommandHandlerDefinition();
    // }

    // @Bean
    // public HandlerEnhancerDefinition commandHandlerInterceptorDefinition() {
    //     return new MethodCommandHandlerDefinition();
    // }
    //
    // @Bean
    // public HandlerDefinition myHandlerDefinition() {
    //     return new MyHandlerDefinition();
    // }

    @Bean
    public EventSourcingRepository<TestAggregate> testAggregateEventSourcingRepository() {
        return EventSourcingRepository.builder(TestAggregate.class).eventStore(eventStore).build();
    }

    @Autowired
    public void setCommandBus() {
        // commandBus.registerHandlerInterceptor(new AxonMessageHandler());
        // commandBus.registerDispatchInterceptor(new AxonMessageDispatcher());
    }

    // @Bean
    // public CommandBus setCommandGateway() {
    //     CommandBus commandBus = SimpleCommandBus.builder().build();
    //     commandBus.registerHandlerInterceptor(new AxonMessageHandler());
    //     commandBus.registerDispatchInterceptor(new AxonMessageDispatcher());
    //     return commandBus;
    // }




}
