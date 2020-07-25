package com.dj.ssm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dj.ssm.mapper.FellMapper;
import com.dj.ssm.pojo.Fell;
import com.dj.ssm.service.FellService;
import org.springframework.stereotype.Service;

@Service
public class FellServiceImpl extends ServiceImpl<FellMapper, Fell> implements FellService {
}
