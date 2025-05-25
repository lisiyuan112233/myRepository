package com.sia.mp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@TableName("user")
public class user {
    private Integer id;
    private String name;
    private Integer age;
    private String email;

}
