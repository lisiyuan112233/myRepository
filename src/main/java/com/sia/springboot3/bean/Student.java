package com.sia.springboot3.bean;

import java.io.Serializable;
import lombok.Data;

/**
 * student
 */
@Data
public class Student implements Serializable {
    /**
     * 学号
     */
    private String s;

    /**
     * 学生姓名
     */
    private String sname;

    /**
     * 学生性别
     */
    private String ssex;

    /**
     * 学生年龄
     */
    private Integer sage;

    /**
     * 所属院系
     */
    private String d;

    /**
     * 班级
     */
    private String sclass;

    private static final long serialVersionUID = 1L;
}