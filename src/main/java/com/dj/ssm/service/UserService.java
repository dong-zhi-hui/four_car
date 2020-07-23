package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.UserMapper;
import com.dj.ssm.pojo.User;

public interface UserService extends IService<User> {

    boolean saveUser(User user);

}
