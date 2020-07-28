package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.config.SystemConstant;
import com.dj.ssm.mapper.FellMapper;
import com.dj.ssm.mapper.OrderCarMapper;
import com.dj.ssm.mapper.TruckMapper;
import com.dj.ssm.pojo.Fell;
import com.dj.ssm.pojo.OrderCar;
import com.dj.ssm.pojo.TruckSpace;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author zhw
 */
@Service
public class TruckServiceImpl extends ServiceImpl<TruckMapper, TruckSpace> implements TruckService {

    @Autowired
    private TruckMapper truckMapper;

    @Autowired
    private OrderCarMapper orderCarMapper;

    @Autowired
    private FellMapper fellMapper;

    @Override
    public Integer findTruckByCount(int i) throws Exception {
        return truckMapper.findTruckByCount(i);
    }

    @Override
    public ResultModel updateTruckAndSaveOrderCar(TruckSpace truckSpace, User user) throws Exception {
        //判断等级为用户0是 并且是会员车位  不能预约
        TruckSpace truckServiceById = truckMapper.selectById(truckSpace.getId());
        if (user.getLevel().equals(SystemConstant.USER_LEVEL) && truckServiceById.getCarLevel().equals(SystemConstant.VIP_CAR)) {
            return new ResultModel().error("不能停会员车位");
        }

        //判断登录username 有没有订单为待付款的
        QueryWrapper<OrderCar> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        List<OrderCar> list = orderCarMapper.selectList(queryWrapper);
        for (OrderCar one : list) {
            if (one != null && one.getOrderStatus() == SystemConstant.NO_PAY) {
                return new ResultModel().error("您已经停过车");
            }
        }

        truckMapper.updateById(truckSpace);

        //添加订单信息
        OrderCar orderCar = new OrderCar();
        String odd = "DJ" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        orderCar.setOrderNumber(odd);
        orderCar.setUserName(user.getUserName());
        orderCar.setPhone(user.getPhone());
        orderCar.setCreateTime(LocalDateTime.now());
        orderCar.setPlateNumber(user.getPlateNumber());
        orderCar.setCarNumber(truckServiceById.getCarNumber());

        //用户等级为1的会员 打九折
        if (user.getLevel() == SystemConstant.USER_VIP) {
            orderCar.setPrice(truckServiceById.getPrice().multiply(BigDecimal.valueOf(SystemConstant.JIUZHE)));
            //   //用户等级为2的高级会员 打八折
        } else if (user.getLevel() == SystemConstant.USER_HIGH_VIP) {
            orderCar.setPrice(truckServiceById.getPrice().multiply(BigDecimal.valueOf(SystemConstant.BAZHE)));
            QueryWrapper<Fell> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", user.getId());
            Fell fell = fellMapper.selectOne(wrapper);
            // 如果免费次数大于0并且fell不等于null 已免次数+1 订单价格为0
            if (null != fell && fell.getFreeCount() > SystemConstant.LING) {
                fell.setFailureCount(fell.getFailureCount() + SystemConstant.ONE);
                fellMapper.updateById(fell);
                orderCar.setPrice(BigDecimal.valueOf(SystemConstant.LING));
            }
        } else {
            orderCar.setPrice(truckServiceById.getPrice());
        }
        orderCar.setOrderStatus(SystemConstant.NO_PAY);

        orderCarMapper.insert(orderCar);
        return new ResultModel().success(true);
    }
}
