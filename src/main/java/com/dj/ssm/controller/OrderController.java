package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.pojo.Order;
import com.dj.ssm.pojo.OrderQuery;
import com.dj.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhw
 */
@RequestMapping("/order/")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("show")
    public ResultModel show(OrderQuery orderQuery){
        try {
            Map<String, Object> map = new HashMap<>();
            Page<Order> page = new Page<>(orderQuery.getPageNo(), orderQuery.getPageSize());
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            if(StringUtils.hasText(orderQuery.getUserName())){
                queryWrapper.eq("user_name", orderQuery.getUserName());
            }
            IPage<Order> orderIPage = orderService.page(page, queryWrapper);
            map.put("list", orderIPage.getRecords());
            map.put("pages", orderIPage.getPages());
            return new ResultModel().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }







}
