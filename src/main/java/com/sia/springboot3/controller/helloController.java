package com.sia.springboot3.controller;
import com.sia.springboot3.Utils.zooKeeperLock;
import com.sia.springboot3.bean.User2;
import com.sia.springboot3.service.userService;
import com.sia.springboot3.Mapper.StudentMapper;
import com.sia.springboot3.bean.good;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Tag(name = "瞎写的")
@RestController
public class helloController {
    @Autowired
    private userService userService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private CuratorFramework build;
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
    @GetMapping("/getLock")
    public String getLock() throws InterruptedException, IOException {
        String result = "";
        zooKeeperLock lock = new zooKeeperLock(build);
        if (lock.tryLock()){
            result= "获取锁成功";
            lock.unLock();
        }else {
            result= "获取锁失败";
        }

        return result;
    }
    @GetMapping("/getLock1")
    public String getLock1() throws Exception {
        InterProcessMutex lock = new InterProcessMutex(build, "/locks/lock-path");
        lock.acquire();
        String result = "获取锁成功";
        lock.release();
        return result;
    }

}
