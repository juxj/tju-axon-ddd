package com.example.demo.validator;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TestCommandValidatorAspect {


    @Pointcut(value = "@annotation(testCommandValidator)")
    protected void setTestCommandValidator(TestCommandValidator testCommandValidator) {
    }


    @Around(value = "setTestCommandValidator(testCommandValidator)", argNames = "p,testCommandValidator")
    public Object around(ProceedingJoinPoint p, TestCommandValidator testCommandValidator) throws Throwable {
        log.info("......");
        return p.proceed();
    }

}