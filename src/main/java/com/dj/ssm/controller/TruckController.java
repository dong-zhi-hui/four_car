package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.pojo.TruckSpace;
import com.dj.ssm.pojo.TruckSpaceQuery;
import com.dj.ssm.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("show")
    public ResultModel show(TruckSpaceQuery truckSpaceQuery){
        try {
            Map<String, Object> map = new HashMap<>();
            Page<TruckSpace> page = new Page<>(truckSpaceQuery.getPageNo(), truckSpaceQuery.getPageSize());
            QueryWrapper<TruckSpace> queryWrapper = new QueryWrapper<>();
            if(StringUtils.hasText(truckSpaceQuery.getCarNumber())){
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

    @RequestMapping("update")
    public ResultModel update(TruckSpace truckSpace){
        try {
            truckService.updateById(truckSpace);
            return new ResultModel().success(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    @RequestMapping("del")
    public ResultModel del(Integer id){
        try {
            return new ResultModel().success(truckService.removeById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }

    @RequestMapping("add")
    public ResultModel add(TruckSpace truckSpace){
        try {
            truckService.save(truckSpace);
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }

    }

    @RequestMapping("findCarNumber")
    public Boolean findCarNumber(TruckSpace truckSpace){
        try {
            QueryWrapper<TruckSpace> queryWrapper = new QueryWrapper<>();
            if(StringUtils.hasText(truckSpace.getCarNumber())){
                queryWrapper.eq("car_number", truckSpace.getCarNumber());
            }
            TruckSpace t = truckService.getOne(queryWrapper);
            return t == null ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }






}
