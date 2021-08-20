package com.example.demo.configuration;

import com.example.demo.validator.TestCommandValidator;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.annotation.HandlerEnhancerDefinition;
import org.axonframework.messaging.annotation.MessageHandlingMember;
import org.axonframework.messaging.annotation.WrappedMessageHandlingMember;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 此处可以注入Spring容器内其他组件，可进行数据库查询或逻辑计算，需要针对每个聚合构建一个单独的拦截器，操作上相繁琐，但能解决问题
 */

@Slf4j
@Component
public class TestValidatorCommandHandlerDefinition implements HandlerEnhancerDefinition {

    public TestValidatorCommandHandlerDefinition() {
    }

    @Override
    public <T> MessageHandlingMember<T> wrapHandler(MessageHandlingMember<T> original) {
        return original.annotationAttributes(TestCommandValidator.class)
                .map(attr -> (MessageHandlingMember<T>) new MethodCommandMessageHandlingMember<>(
                        original, attr))
                .orElse(original);
    }

    private static class MethodCommandMessageHandlingMember<T>
            extends WrappedMessageHandlingMember<T> {


        private final String commandName;

        private MethodCommandMessageHandlingMember(MessageHandlingMember<T> delegate,
                                                   Map<String, Object> annotationAttributes) {
            super(delegate);
            if ("".equals(annotationAttributes.get("name"))) {
                commandName = delegate.payloadType().getName();
            } else {
                commandName = (String) annotationAttributes.get("name");
            }
            log.info(commandName);
        }

        @Override
        public boolean canHandle(Message<?> message) {
            log.info("x:{}", message.getIdentifier());
            // return super.canHandle(message);
            // String tmp = ((CommandMessage) message).getCommandName();
            return super.canHandle(message);
        }

        public String commandName() {
            return commandName;
        }
    }
}