package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.TruckMapper;
import com.dj.ssm.pojo.TruckSpace;
import com.dj.ssm.service.TruckService;
import org.springframework.stereotype.Service;

@Service
public class TruckServiceImpl extends ServiceImpl<TruckMapper, TruckSpace> implements TruckService {
}
