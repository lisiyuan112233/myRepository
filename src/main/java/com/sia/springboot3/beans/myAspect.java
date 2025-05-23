package com.sia.springboot3.beans;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class myAspect {
    @Pointcut("execution(* com.sia.springboot3.beans.bean2.*(..)))")
    public void myPointcut() {
    }
    @Before("myPointcut()")
    public void before() {
        System.out.println("before");
    }
}
