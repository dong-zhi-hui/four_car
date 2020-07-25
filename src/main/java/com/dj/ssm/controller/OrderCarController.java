package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.config.SystemConstant;
import com.dj.ssm.pojo.*;
import com.dj.ssm.service.LocusService;
import com.dj.ssm.service.OrderCarService;
import com.dj.ssm.service.TruckService;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhw
 */
@RequestMapping("/order/")
@RestController
public class OrderCarController {

    @Autowired
    private OrderCarService orderCarService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private LocusService locusService;

    @Autowired
    private UserService userService;

    /**
     * 订单展示
     *
     * @param orderCarQuery
     * @param user
     * @return
     */
    @RequestMapping("show")
    public ResultModel show(OrderCarQuery orderCarQuery, @SessionAttribute("user") User user) {
        try {
            Map<String, Object> map = new HashMap<>();
            Page<OrderCar> page = new Page<>(orderCarQuery.getPageNo(), orderCarQuery.getPageSize());
            QueryWrapper<OrderCar> queryWrapper = new QueryWrapper<>();
            if (StringUtils.hasText(orderCarQuery.getUserName())) {
                queryWrapper.eq("user_name", orderCarQuery.getUserName());
            }
            if (user.getLevel() != SystemConstant.USERLEVEL) {
                queryWrapper.eq("user_name", user.getUserName());
            }
            IPage<OrderCar> orderIPage = orderCarService.page(page, queryWrapper);
            map.put("list", orderIPage.getRecords());
            map.put("pages", orderIPage.getPages());
            return new ResultModel().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    /**
     * 订单删除
     *
     * @param id
     * @return
     */
    @RequestMapping("del")
    public ResultModel del(Integer id) {
        try {
            return new ResultModel().success(orderCarService.removeById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    @RequestMapping("updatePay")
    public ResultModel updatePay(OrderCar orderCar, String pay, @SessionAttribute("user") User user) {
        try {
            orderCarService.updateById(orderCar);
            OrderCar orderCar1 = orderCarService.getById(orderCar.getId());
            if (orderCar1 != null && orderCar1.getOrderStatus() == 1) {
                TruckSpace truckSpace = new TruckSpace();
                truckSpace.setCarStatus(0);
                UpdateWrapper<TruckSpace> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("car_number", orderCar1.getCarNumber());
                updateWrapper.set("car_status", truckSpace.getCarStatus());
                truckService.update(updateWrapper);
            }
            if (orderCar.getOrderStatus() == SystemConstant.YES_PAY) {
                Locus locus = new Locus();
                locus.setAction(pay);
                locus.setOrderDate(LocalDateTime.now());
                locus.setOrderId(orderCar.getId());
                locus.setUserName(user.getUserName());
                locusService.save(locus);
            }
            QueryWrapper<OrderCar> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name", user.getUserName());
            BigDecimal big = new BigDecimal(0);
            List<OrderCar> list = orderCarService.list(queryWrapper);
            for (OrderCar one : list) {
                if(one != null){
                    big = big.add(one.getPrice());
                }
            }

            QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("user_name", user.getUserName());
            User one = userService.getOne(queryWrapper1);
            if(Double.valueOf(String.valueOf(big)) >= 200 && Double.valueOf(String.valueOf(big)) < 500){
                one.setLevel(1);
                userService.updateById(one);
            }else if(Double.valueOf(String.valueOf(big)) >= 500){
                one.setLevel(2);
                userService.updateById(one);
            }
            return new ResultModel().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }


}
