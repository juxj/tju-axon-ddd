package com.example.demo.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class TestAspectRunner {

    /**
     * This pointcut will call respective before and after method execution
     * points
     */
    @Pointcut("execution(* com.example.demo.aggregate.TestAggregate.*(..))")
    public void logging(){};

    @Before("logging()")
    public void entering(JoinPoint joinPoint)
    {
        System.out.println("After completing Class : "+joinPoint.getTarget().getClass().getName() +" and method : "+joinPoint.getSignature().getName());
    }

    @After("logging()")
    public void exiting(JoinPoint joinPoint)
    {
        System.out.println("After completing Class : "+joinPoint.getTarget().getClass().getName() +" and method : "+joinPoint.getSignature().getName());
    }
}
