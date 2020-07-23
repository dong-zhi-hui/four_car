package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.UserMapper;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.zip.DataFormatException;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

//    @Autowired
//    private UserMapper userMapper;

    @Override
    public boolean saveUser(User user) {
        this.getBaseMapper().insert(user);
        return true;
    }
}
