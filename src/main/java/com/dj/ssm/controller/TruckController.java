package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.config.SystemConstant;
import com.dj.ssm.pojo.*;
import com.dj.ssm.service.FellService;
import com.dj.ssm.service.OrderCarService;
import com.dj.ssm.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author zhw
 */
@RestController
@RequestMapping("/truck/")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @Autowired
    private OrderCarService orderCarService;

    @Autowired
    private FellService fellService;

    /**
     * 车位管理展示
     *
     * @param truckSpaceQuery
     * @return
     */
    @RequestMapping("show")
    public ResultModel show(TruckSpaceQuery truckSpaceQuery) {
        try {
            Map<String, Object> map = new HashMap<>();
            Page<TruckSpace> page = new Page<>(truckSpaceQuery.getPageNo(), truckSpaceQuery.getPageSize());
            QueryWrapper<TruckSpace> queryWrapper = new QueryWrapper<>();
            if (StringUtils.hasText(truckSpaceQuery.getCarNumber())) {
                queryWrapper.eq("car_number", truckSpaceQuery.getCarNumber());
            }
            IPage<TruckSpace> truckIPage = truckService.page(page, queryWrapper);
            map.put("list", truckIPage.getRecords());
            map.put("pages", truckIPage.getPages());
            return new ResultModel().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    /**
     * 更改车辆管理状态 空置已预约
     * @param truckSpace
     * @param user
     * @return
     */
    @RequestMapping("update")
    public ResultModel update(TruckSpace truckSpace, @SessionAttribute("user") User user) {
        try {
            //判断等级为用户0是 并且是会员车位  不能预约
            TruckSpace truckServiceById = truckService.getById(truckSpace.getId());
            if (user.getLevel().equals(SystemConstant.USER_LEVEL) && truckServiceById.getCarLevel().equals(SystemConstant.VIP_CAR)) {
                return new ResultModel().error("不能停会员车位");
            }
            //判断登录username 有没有订单为待付款的
            QueryWrapper<OrderCar> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name", user.getUserName());
            List<OrderCar> list = orderCarService.list(queryWrapper);
            for (OrderCar one : list) {
                if (one != null && one.getOrderStatus() == SystemConstant.NO_PAY) {
                    return new ResultModel().error("您已经停过车");
                }
            }

            truckService.updateById(truckSpace);
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
            if (user.getLevel() == SystemConstant.USER_VIP){
                 orderCar.setPrice(truckServiceById.getPrice().multiply(BigDecimal.valueOf(SystemConstant.JIUZHE)));
                 //   //用户等级为2的高级会员 打八折
            } else if (user.getLevel() == SystemConstant.USER_HIGH_VIP){
                orderCar.setPrice(truckServiceById.getPrice().multiply(BigDecimal.valueOf(SystemConstant.BAZHE)));
                QueryWrapper<Fell> wrapper = new QueryWrapper<>();
                wrapper.eq("user_id",user.getId());
                Fell fell = fellService.getOne(wrapper);
                // 如果免费次数大于0并且fell不等于null 已免次数+1 订单价格为0
                if(null != fell && fell.getFreeCount() > SystemConstant.LING) {
                    fell.setFailureCount(fell.getFailureCount()+SystemConstant.ONE);
                    fellService.updateById(fell);
                    orderCar.setPrice(BigDecimal.valueOf(SystemConstant.LING));
                }
            }else {
                orderCar.setPrice(truckServiceById.getPrice());
            }
            orderCar.setOrderStatus(SystemConstant.NO_PAY);
            orderCarService.save(orderCar);
            return new ResultModel().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    /**
     * 车位删除
     *
     * @param id
     * @return
     */
    @RequestMapping("del")
    public ResultModel del(Integer id) {
        try {
            return new ResultModel().success(truckService.removeById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    /**
     * 增加车位
     *
     * @param truckSpace
     * @return
     */
    @RequestMapping("add")
    public ResultModel add(TruckSpace truckSpace) {
        try {
            truckService.save(truckSpace);
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }

    }

    /**
     * 去重车位编号
     *
     * @param truckSpace
     * @return
     */
    @RequestMapping("findCarNumber")
    public Boolean findCarNumber(TruckSpace truckSpace) {
        try {
            QueryWrapper<TruckSpace> queryWrapper = new QueryWrapper<>();
            if (StringUtils.hasText(truckSpace.getCarNumber())) {
                queryWrapper.eq("car_number", truckSpace.getCarNumber());
            }
            TruckSpace t = truckService.getOne(queryWrapper);
            return t == null ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * echars展示
     *
     * @return
     */
    @RequestMapping("findTruckByCount")
    public ResultModel findTruckByCount() {
        Map<String, Object> map = new HashMap<>();
        try {
            //空置
            Integer count1 = truckService.findTruckByCount(SystemConstant.PARKING_STATE_ZERO);
            //已预约
            Integer count2 = truckService.findTruckByCount(SystemConstant.PARKING_STATE_ONE);
            //总车位
            Integer countAll = count1 + count2;
            map.put("countAll", countAll);
            map.put("count1", count1);
            map.put("count2", count2);
            return new ResultModel().success(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后再试");
        }
    }


}
