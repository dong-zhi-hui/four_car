package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.config.SystemConstant;
import com.dj.ssm.mapper.*;
import com.dj.ssm.pojo.*;
import com.dj.ssm.service.OrderCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zhw
 */
@Service
public class OrderCarServiceImpl extends ServiceImpl<OrderCarMapper, OrderCar> implements OrderCarService {

    @Autowired
    private OrderCarMapper orderCarMapper;

    @Autowired
    private TruckMapper truckMapper;

    @Autowired
    private LocusMapper locusMapper;

    @Autowired
    private FellMapper fellMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultModel updateOrderCarAndSaveLocusAndSaveFell(OrderCar orderCar, String pay, User user, HttpSession session) throws Exception {
        orderCarMapper.updateById(orderCar);
        //订单完成车位变成空置
        OrderCar orderCar1 = orderCarMapper.selectById(orderCar.getId());
        if (orderCar1 != null && orderCar1.getOrderStatus() == SystemConstant.YES_PAY) {
            TruckSpace truckSpace = new TruckSpace();
            truckSpace.setCarStatus(SystemConstant.PARKING_STATE_ZERO);
            UpdateWrapper<TruckSpace> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("car_number", orderCar1.getCarNumber());
            updateWrapper.set("car_status", truckSpace.getCarStatus());
            truckMapper.update(truckSpace, updateWrapper);
        }

        //添加支付信息轨迹
        if (orderCar.getOrderStatus() == SystemConstant.YES_PAY) {
            Locus locus = new Locus();
            locus.setAction(pay);
            locus.setOrderDate(LocalDateTime.now());
            locus.setOrderId(orderCar.getId());
            locus.setUserName(user.getUserName());
            locusMapper.insert(locus);
        }

        //计算总金额
        QueryWrapper<OrderCar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        BigDecimal money = new BigDecimal(SystemConstant.LING);
        List<OrderCar> list = orderCarMapper.selectList(queryWrapper);
        for (OrderCar one : list) {
            if (one != null) {
                money = money.add(one.getPrice());
            }
        }

        //如果消费金额大于500 免费赠送一次
        if (Double.valueOf(String.valueOf(money)) > SystemConstant.WUBAI) {
            QueryWrapper<Fell> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", user.getId());
            Fell fell = fellMapper.selectOne(wrapper);
            if (null == fell) {
                Fell f = new Fell();
                f.setUserId(user.getId());
                // 免费次数 = 消费金额/500
                f.setFreeCount(Integer.valueOf(String.valueOf(money.divide(new BigDecimal(SystemConstant.WUBAI)).
                        setScale(SystemConstant.LING, BigDecimal.ROUND_DOWN))));
                f.setFailureCount(SystemConstant.LING);
                fellMapper.insert(f);
            } else {
                // 剩余免费次数 = 消费金额/500-使用次数
                fell.setFreeCount(Integer.valueOf(String.valueOf(money.divide(new BigDecimal(SystemConstant.WUBAI))
                        .setScale(SystemConstant.LING, BigDecimal.ROUND_DOWN))) - fell.getFailureCount());
                fellMapper.updateById(fell);
            }
        }

        //判断金额为200为普通会员 500高级会员
        QueryWrapper<User> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_name", user.getUserName());
        User one = userMapper.selectOne(queryWrapper1);
        if (Double.valueOf(String.valueOf(money)) >= SystemConstant.ERBAI && Double.valueOf(String.valueOf(money))
                < SystemConstant.WUBAI) {
            one.setLevel(SystemConstant.USER_VIP);
            userMapper.updateById(one);
        } else if (Double.valueOf(String.valueOf(money)) >= SystemConstant.WUBAI) {
            one.setLevel(SystemConstant.USER_HIGH_VIP);
            userMapper.updateById(one);
        }
        session.setAttribute("user", one);
        return new ResultModel().success(true);
    }
}
