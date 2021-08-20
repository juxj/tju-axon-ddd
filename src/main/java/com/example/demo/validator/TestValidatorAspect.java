package com.example.demo.validator;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TestValidatorAspect  {

    @Pointcut(value = "@annotation(validator)")
    protected void setValidator(TestCommandValidator validator) {
    }

    @Before("@annotation(com.example.demo.validator.TestCommandValidator)")
    public void print(JoinPoint joinPoint) {
        System.out.println("Printing from aspect! Arguments : " + joinPoint.getArgs());
    }
}