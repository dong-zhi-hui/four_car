package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.OrderMapper;
import com.dj.ssm.pojo.Order;
import com.dj.ssm.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author zhw
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
