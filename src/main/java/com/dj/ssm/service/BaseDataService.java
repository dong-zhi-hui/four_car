package com.dj.ssm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dj.ssm.pojo.BaseData;

import java.util.List;

public interface BaseDataService extends IService<BaseData> {

    List<BaseData> findByParentId(Integer parentId) throws Exception;
}
