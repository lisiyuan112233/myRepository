package com.sia.springboot3.Mapper;

import com.sia.springboot3.bean.User2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface userMapper {
    List<User2> selectUser();
}
