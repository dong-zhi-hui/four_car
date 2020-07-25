package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.LocusMapper;
import com.dj.ssm.pojo.Locus;
import com.dj.ssm.service.LocusService;
import org.springframework.stereotype.Service;

@Service
public class LocusServiceImpl extends ServiceImpl <LocusMapper, Locus> implements LocusService {
}
