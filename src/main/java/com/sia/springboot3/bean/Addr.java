package com.sia.springboot3.bean;

import jakarta.annotation.PostConstruct;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
//        ArrayList<String> strings = new ArrayList<>(List.of("23", "645", "4534"));
//        strings.sort(String::compareTo);
//        strings.forEach(System.out::println);
//        System.out.println("sda".lastIndexOf("d"));
//        System.out.println("adfd-sad".substring(4));
//        List<Integer> i= new ArrayList<>(Arrays.asList(1,45,33,132));
//        i.forEach(System.out::println);
//        i.sort((a,b)-> a.compareTo(b));
//        i.forEach(System.out::println);
        String a="seq-00000000002121";
        Integer a1= Integer.valueOf(a.substring(a.lastIndexOf("-")+1));
        System.out.println(a1);
    }
}
