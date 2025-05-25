package com.sia.springboot3;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sia.springboot3.Mapper.StudentMapper;
import com.sia.springboot3.bean.User;
import com.sia.springboot3.bean.good;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@SpringBootTest
@ConfigurationProperties(prefix = "myprop.service")
class SpringBoot3ApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Environment environment;
    @Autowired
    private User user;
    @Autowired
    private good good;
    @Autowired
    private CuratorFramework build;
    @Autowired
    private StudentMapper studentMapper;
    @Value("${myprop.service.username}")
    private String uname;
    private String uname2;

    private String password;


    public void setUsername(String uname2) {
        this.uname2 = uname2;
    }

    @Test
    void contextLoads() {
        System.out.println(uname);
    }

    @Test
    void test03() {
        Arrays.stream(environment.getDefaultProfiles()).forEach(System.out::println);
    }

    @Test
    void Test04() {
        System.out.println(good.getCode());
    }

    @Test
    void Test05() {
        System.out.println(studentMapper.selectByPrimaryKey("98030101"));
    }

    @Test
    @SuppressWarnings("all")
    void TestRedis() throws JsonProcessingException {
//        stringRedisTemplate.opsForValue().set("code1",objectMapper.writeValueAsString(new good("123")));
        good good = objectMapper.readValue(stringRedisTemplate.opsForValue().get("code1"), good.class);
        System.out.println(good);
    }

    @Test
    @SuppressWarnings("all")
    void TestRedis2() throws JsonProcessingException {
        stringRedisTemplate.opsForHash().put("hash", "name", "张三");
        stringRedisTemplate.opsForHash().put("hash", "age", "18");
        stringRedisTemplate.opsForHash().entries("hash").forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });
    }

    @Test
    void TestRedisSentinel() {
        System.out.println(stringRedisTemplate.opsForValue().get("code1"));
    }
    @Test

    public void TestZookeeper() throws Exception {
//forPath("Lock-seq-")

        for (int i = 0; i < 10; i++){
             build.create().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath("/Lock/seq-");
        }
        build.getChildren().forPath("/Lock").forEach(System.out::println);
    }

}