package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.ssm.pojo.BaseData;

import java.util.List;

public interface BaseDataMapper extends BaseMapper<BaseData> {

    List<BaseData> findByParentId(Integer parentId) throws Exception;
}
