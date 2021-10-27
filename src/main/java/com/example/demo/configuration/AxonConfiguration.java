package com.example.demo.configuration;

import com.example.demo.aggregate.UserAggregate;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPPublisher;
import org.axonframework.messaging.annotation.HandlerDefinition;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Slf4j
@Configuration
public class AxonConfiguration {


    @Resource
    private CommandBus commandBus;

    @Resource
    private EventStore eventStore;

    @Bean
    public HandlerDefinition myHandlerDefinition() {
        return new MyHandlerDefinition();
    }

    @Autowired
    public void config(EventProcessingConfigurer configurer) {
        configurer.usingSubscribingEventProcessors();
    }

    /**
     * 接收并处理 RabbitMQ 发出来的消息.
     * @param messageConverter .
     * @return .
     */
    @Bean
    public SpringAMQPMessageSource inputMessageSource(final AMQPMessageConverter messageConverter) {
        return new SpringAMQPMessageSource(messageConverter) {
            @RabbitListener(queues = "events")
            @Override
            public void onMessage(final Message message, final Channel channel) {
                log.info("received external message: {}, channel: {}", message, channel);
                super.onMessage(message, channel);
            }
        };
    }


    /**
     * 连接 RabbitMQ. 用于异步消息发送.
     * @param eventBus .
     * @param connectionFactory .
     * @param amqpMessageConverter .
     * @return .
     */
    @Bean(initMethod = "start", destroyMethod = "shutDown")
    public SpringAMQPPublisher publisher(
            EventBus eventBus,
            ConnectionFactory connectionFactory,
            AMQPMessageConverter amqpMessageConverter) {
        SpringAMQPPublisher publisher = new SelectiveAmqpPublisher(eventBus);
        // The rest is from axon-spring-autoconfigure...
        publisher.setExchangeName("axon");
        publisher.setConnectionFactory(connectionFactory);
        publisher.setMessageConverter(amqpMessageConverter);
        publisher.setWaitForPublisherAck(true);
        return publisher;
    }


    /**
     * 注册聚合仓库
     * @return .
     */
    @Bean
    public EventSourcingRepository<UserAggregate> testAggregateEventSourcingRepository() {
        return EventSourcingRepository.builder(UserAggregate.class).eventStore(eventStore).build();
    }


    /**
     * 在CommandBus中注册消息处理器.
     */
    @Autowired
    public void setCommandBus() {
        commandBus.registerHandlerInterceptor(new AxonMessageHandler());
        commandBus.registerDispatchInterceptor(new TestMessageDispatchInterceptor());
    }

    // @Bean
    // public CommandBus setCommandGateway() {
    //     CommandBus commandBus = SimpleCommandBus.builder().build();
    //     commandBus.registerHandlerInterceptor(new AxonMessageHandler());
    //     commandBus.registerDispatchInterceptor(new AxonMessageDispatcher());
    //     return commandBus;
    // }


}
