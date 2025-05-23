package com.sia.springboot3.Mapper;

import com.sia.springboot3.bean.Student;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Mapper
public interface StudentMapper {
    int deleteByPrimaryKey(String s);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String s);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}