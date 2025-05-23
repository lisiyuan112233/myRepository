package com.sia.springboot3.controller;
import com.sia.springboot3.bean.User2;
import com.sia.springboot3.service.userService;
import com.sia.springboot3.Mapper.StudentMapper;
import com.sia.springboot3.bean.good;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.List;
@Tag(name = "瞎写的")
@RestController
public class helloController {
    @Autowired
    private userService userService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/hello")
    public good hello(@RequestBody good good){
        System.out.println(good.getCode());
        return good;
    }
    @RequestMapping("/hello1")
    public Object hello1(){
        List<User2> user2s = userService.selectUser();
        return studentMapper.selectByPrimaryKey("98030101");

    }
    @RequestMapping("/count")
    public Long count(){
        return stringRedisTemplate.opsForValue().increment("count");
    }
    @Tag(name = "说你好")
    @GetMapping("/hello2")
    public String hello(){
        return "hello";
    }

}
