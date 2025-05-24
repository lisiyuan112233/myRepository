package com.sia.springboot3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class aspectj {

    @Pointcut("execution(* com.sia.springboot3.bean..*(..))")
    public void pointcut() {
    }
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before"+joinPoint.getSignature().getName());
    }
}
