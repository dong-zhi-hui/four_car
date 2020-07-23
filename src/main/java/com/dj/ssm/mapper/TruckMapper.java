package com.dj.ssm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dj.ssm.pojo.TruckSpace;
import org.springframework.dao.DataAccessException;

/**
 * @author zhw
 */
public interface TruckMapper extends BaseMapper<TruckSpace> {

    Integer findTruckByCount(int i) throws DataAccessException;
}
