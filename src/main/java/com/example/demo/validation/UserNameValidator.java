package com.example.demo.validation;

import org.axonframework.messaging.annotation.MessageHandlingMember;

import java.lang.annotation.*;

/**
 * 标有TestCommandValidator的标签，在执行Command之前需要经过{@link UserNameValidatorHandlerDefinition#wrapHandler(MessageHandlingMember)}）
 */
@Inherited
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameValidator {
    String name() default "";
}