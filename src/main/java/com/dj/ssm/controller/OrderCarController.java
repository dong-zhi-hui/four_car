package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.config.SystemConstant;
import com.dj.ssm.pojo.OrderCar;
import com.dj.ssm.pojo.OrderCarQuery;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单管理
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

    @Autowired
    private FellService fellService;

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
            queryWrapper.orderByDesc("create_time");
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

    /**
     * 修改订单状态 把待付款改为订单完成
     *
     * @param orderCar
     * @param pay
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("updatePay")
    public ResultModel updatePay(OrderCar orderCar, String pay, @SessionAttribute("user") User user, HttpSession session) {
        try {
            return orderCarService.updateOrderCarAndSaveLocusAndSaveFell(orderCar,pay,user,session);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }
}
