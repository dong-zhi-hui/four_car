package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.pojo.Message;
import com.dj.ssm.pojo.TruckSpaceQuery;
import com.dj.ssm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * MessageController
 */
@RestController
@RequestMapping("/message/")
public class MessageController {

    @Autowired
    private MessageService messageService;

    //留言板展示
    @RequestMapping("show")
    public ResultModel show(TruckSpaceQuery truckSpaceQuery){
        try {
            Map<String, Object> map = new HashMap<>();
            Page<Message> page = new Page<>(truckSpaceQuery.getPageNo(), truckSpaceQuery.getPageSize());
            QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
            if(StringUtils.hasText(truckSpaceQuery.getMessageContents())){
                queryWrapper.like("message_contents", truckSpaceQuery.getMessageContents());
            }
            IPage<Message> userIPage = messageService.page(page, queryWrapper);
            map.put("list", userIPage.getRecords());
            map.put("pages", userIPage.getPages());
            return new ResultModel().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error(e.getMessage());
        }
    }










}
