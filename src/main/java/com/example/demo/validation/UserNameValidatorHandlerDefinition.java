package com.example.demo.validation;

import com.example.demo.service.IAggregateCommandValidator;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.annotation.HandlerEnhancerDefinition;
import org.axonframework.messaging.annotation.MessageHandlingMember;
import org.axonframework.messaging.annotation.WrappedMessageHandlingMember;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.annotation.Annotation;
import java.util.Map;


/**
 * 1. 此处可以注入Spring容器内其他组件，
 * 2. 可进行数据库查询或逻辑计算，
 * 3. 需要针对每个聚合构建一个单独的拦截器，
 * 4. 操作上相繁琐，但能解决问题
 * 5. 建议在业务模块中定义，不需要在Axon通用配置中定义
 */

@Slf4j
@Component
public class UserNameValidatorHandlerDefinition implements HandlerEnhancerDefinition {

    @Resource
    private IAggregateCommandValidator validator;

    public UserNameValidatorHandlerDefinition() {
    }

    @Override
    public <T> MessageHandlingMember<T> wrapHandler(MessageHandlingMember<T> original) {
        return original.annotationAttributes(UserNameValidator.class)
                .map(attr -> (MessageHandlingMember<T>) new MethodCommandMessageHandlingMember<>(
                        original, attr, validator))
                .orElse(original);
    }

    private static class MethodCommandMessageHandlingMember<T>
            extends WrappedMessageHandlingMember<T> {

        private final String commandName;

        private final IAggregateCommandValidator aggregateCommandValidator;

        private MethodCommandMessageHandlingMember(MessageHandlingMember<T> delegate,
                                                   Map<String, Object> annotationAttributes, IAggregateCommandValidator validator) {
            super(delegate);
            if ("".equals(annotationAttributes.get("name"))) {
                commandName = delegate.payloadType().getName();
            } else {
                commandName = (String) annotationAttributes.get("name");
            }
            log.info(commandName);
            this.aggregateCommandValidator = validator;
        }

        /**
         * 指定注解的方法才会被拦截
         * @param message .
         * @return .
         */
        @Override
        public boolean canHandle(Message<?> message) {
            try {
                aggregateCommandValidator.validate(message);
                return super.canHandle(message);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            return false;
        }

        @Override
        public boolean canHandleMessageType(Class<? extends Message> messageType) {
            return super.canHandleMessageType(messageType);
        }

        @Override
        public boolean hasAnnotation(Class<? extends Annotation> annotationType) {
            return super.hasAnnotation(annotationType);
        }

    }
}