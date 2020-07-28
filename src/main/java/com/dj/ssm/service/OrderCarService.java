package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.pojo.OrderCar;
import com.dj.ssm.pojo.User;

import javax.servlet.http.HttpSession;

/**
 * @author zhw
 */
public interface OrderCarService extends IService<OrderCar> {

    ResultModel updateOrderCarAndSaveLocusAndSaveFell(OrderCar orderCar, String pay, User user, HttpSession session) throws Exception;


}
