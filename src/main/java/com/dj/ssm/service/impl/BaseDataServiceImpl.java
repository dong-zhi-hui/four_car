package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.BaseDataMapper;
import com.dj.ssm.pojo.BaseData;
import com.dj.ssm.service.BaseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseDataServiceImpl extends ServiceImpl<BaseDataMapper, BaseData> implements BaseDataService {

    @Autowired
    private BaseDataMapper baseDataMapper;

    @Override
    public List<BaseData> findByParentId(Integer parentId) throws Exception {
        return baseDataMapper.findByParentId(parentId);
    }


}
