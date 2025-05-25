package com.sia.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sia.mp.entity.user;
import com.sia.mp.mapper.userMapper;
import com.sia.mp.service.IUserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class IUserServiceImpl extends ServiceImpl<userMapper,user> implements IUserService {

}
