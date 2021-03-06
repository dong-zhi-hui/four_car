package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.config.SystemConstant;
import com.dj.ssm.pojo.TruckSpace;
import com.dj.ssm.pojo.TruckSpaceQuery;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashMap;
import java.util.Map;


/**
 * @author zhw
 */
@RestController
@RequestMapping("/truck/")
public class TruckController {

    @Autowired
    private TruckService truckService;

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
     *
     * @param truckSpace
     * @param user
     * @return
     */
    @RequestMapping("update")
    public ResultModel update(TruckSpace truckSpace, @SessionAttribute("user") User user) {
        try {
            return truckService.updateTruckAndSaveOrderCar(truckSpace, user);
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

    /**
     * 修改车位信息
     *
     * @param truckSpace
     * @return
     */
    @RequestMapping("updateTruck")
    public ResultModel updateTruck(TruckSpace truckSpace) {
        try {
            truckService.updateById(truckSpace);
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后再试");
        }
    }


}
