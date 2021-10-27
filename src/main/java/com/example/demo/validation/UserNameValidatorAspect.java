package com.example.demo.validation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class UserNameValidatorAspect {


    @Pointcut(value = "@annotation(userNameValidator)")
    protected void setTestCommandValidator(UserNameValidator userNameValidator) {
    }


    @Around(value = "setTestCommandValidator(userNameValidator)", argNames = "p,userNameValidator")
    public Object around(ProceedingJoinPoint p, UserNameValidator userNameValidator) throws Throwable {
        log.info("用户名是否重复验证");
        return p.proceed();
    }

}