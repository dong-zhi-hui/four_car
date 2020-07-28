package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.pojo.TruckSpace;
import com.dj.ssm.pojo.User;

/**
 * @author zhw
 */
public interface TruckService extends IService<TruckSpace> {

    Integer findTruckByCount(int i) throws Exception;

    ResultModel updateTruckAndSaveOrderCar(TruckSpace truckSpace, User user) throws Exception;
}
