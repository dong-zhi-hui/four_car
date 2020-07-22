package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.OrderCarMapper;
import com.dj.ssm.pojo.OrderCar;
import com.dj.ssm.service.OrderCarService;
import org.springframework.stereotype.Service;

/**
 * @author zhw
 */
@Service
public class OrderCarServiceImpl extends ServiceImpl<OrderCarMapper, OrderCar> implements OrderCarService {
}
