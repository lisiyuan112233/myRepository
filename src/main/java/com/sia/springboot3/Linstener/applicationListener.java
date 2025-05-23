package com.sia.springboot3.Linstener;

import com.sia.springboot3.event.myApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestContextHolder;

@Component
public class applicationListener implements ApplicationListener<myApplicationEvent> {
    @Override
    public void onApplicationEvent(myApplicationEvent event) {
        System.out.println("收到自定义事件"+event.getData());
        
    }
}
