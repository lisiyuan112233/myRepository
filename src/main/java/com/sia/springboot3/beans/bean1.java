package com.sia.springboot3.beans;

import com.sia.springboot3.event.myApplicationEvent;
import jakarta.annotation.Resource;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class bean1 {
    @EventListener
    public void listen(myApplicationEvent event){
        System.out.println("bean1监听到事件"+event.getData());
    }
}
