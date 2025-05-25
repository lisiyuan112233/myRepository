package com.sia.mp;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.sia.mp.entity.user;
import com.sia.mp.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.reflect.Method;
import java.util.List;

@SpringBootTest
class MpApplicationTests {

    @Autowired
    private IUserService iUserService;
    @Test
    void contextLoads() {
        List<user> users = iUserService.query().eq("id", 2).list();
        System.out.println(users.get(0));
        Class<user> userClass = user.class;
        List.of(userClass.getDeclaredMethods()).forEach(method ->{
            System.out.println(method.getName());
        });
    }

}
