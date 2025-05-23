package com.sia.springboot3.service.impl;
import com.sia.springboot3.Mapper.userMapper;
import com.sia.springboot3.bean.User2;
import com.sia.springboot3.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userMapper userMapper;
    @Override
    public List<User2> selectUser() {
        return userMapper.selectUser();
    }
}
