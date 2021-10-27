package com.example.demo.validator;

import com.example.demo.configuration.TestValidatorCommandHandlerDefinition;
import org.axonframework.messaging.annotation.MessageHandlingMember;

import java.lang.annotation.*;

/**
 * 标有TestCommandValidator的标签，在执行Command之前需要经过{@link TestValidatorCommandHandlerDefinition#wrapHandler(MessageHandlingMember)}）
 */
@Inherited
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameValidator {
    String name() default "";
}