package com.sia.springboot3.beans;

import org.springframework.stereotype.Component;

@Component
public class bean2{
    public bean2(){
        System.out.println("bean2被初始化");
    }
    public void print(){
        System.out.println("bean2被调用");
    }
}
