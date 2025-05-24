package com.sia.springboot3.bean;

import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
@Slf4j
@Builder
public class Addr {
    private String province;
    private String city;

    @PostConstruct
    public void init(){
        System.out.println("Addr init");
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Addr{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Addr addr = Addr.builder().province("北京市").city("北京市").build();
        System.out.println(addr);
        log.info("Addr info");
    }
}
