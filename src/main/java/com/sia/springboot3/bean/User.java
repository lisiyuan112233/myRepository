package com.sia.springboot3.bean;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
@ConfigurationProperties("myuser")
//@PropertySource("classpath:application.properties")
public class User {

    private String name;
    private int age;
    private Addr addr;
    private Addr[] addrs;
    private List<Addr> addrsList;
    private Map<String,Addr> addrsMap;

    @PostConstruct
    public void init(){
        System.out.println("User init");
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAddrsList(List<Addr> addrsList) {
        this.addrsList = addrsList;
    }

    public void setAddrsMap(Map<String, Addr> addrsMap) {
        this.addrsMap = addrsMap;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddr(Addr addr) {
        this.addr = addr;
    }

    public void setAddrs(Addr[] addrs) {
        this.addrs = addrs;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", addr=" + addr +
                ", addrs=" + Arrays.toString(addrs) +
                ", addrsList=" + addrsList +
                ", addrsMap=" + addrsMap +
                '}';
    }

    public static void main(String[] args) {
        System.out.println("User");
    }
}
