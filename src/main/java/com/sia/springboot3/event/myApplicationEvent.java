package com.sia.springboot3.event;

import org.springframework.context.ApplicationEvent;

public class myApplicationEvent extends ApplicationEvent {

    private String data;
    public myApplicationEvent(Object source,String data) {
        super(source);
        this.data=data;
    }
    public String getData(){
        return data;
    }
}
