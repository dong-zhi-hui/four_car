package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.pojo.Message;
import com.dj.ssm.pojo.TruckSpaceQuery;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MessageController
 */
@RestController
@RequestMapping("/message/")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * 留言板展示
     *
     * @param truckSpaceQuery
     * @return
     */
    @RequestMapping("show")
    public ResultModel show(TruckSpaceQuery truckSpaceQuery) {
        try {
            Map<String, Object> map = new HashMap<>();
            Page<Message> page = new Page<>(truckSpaceQuery.getPageNo(), truckSpaceQuery.getPageSize());
            QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
            if (StringUtils.hasText(truckSpaceQuery.getMessageContents())) {
                queryWrapper.like("message_contents", truckSpaceQuery.getMessageContents());
            }
            queryWrapper.orderByDesc("create_time");
            IPage<Message> messageIPage = messageService.page(page, queryWrapper);
            map.put("list", messageIPage.getRecords());
            map.put("pages", messageIPage.getPages());
            return new ResultModel().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后重试");
        }
    }

    /**
     * 新增留言
     *
     * @param message
     * @return
     */
    @RequestMapping("add")
    public ResultModel add(Message message, @SessionAttribute("user") User user) {
        try {
            if (StringUtils.isEmpty(message.getMessageContents())) {
                return new ResultModel().error("留言内容不能为空");
            }
            message.setCreateTime(LocalDateTime.now());
            message.setUserName(user.getUserName());
            messageService.save(message);
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后重试");

        }
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @RequestMapping("del")
    public ResultModel del(Integer id) {
        try {
            messageService.removeById(id);
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后重试");
        }
    }

    /**
     * 回复
     *
     * @param message
     * @param user
     * @return
     */
    @RequestMapping("update")
    public ResultModel update(Message message, @SessionAttribute("user") User user) {
        try {
            if (StringUtils.isEmpty(message.getResponse())) {
                return new ResultModel().error("回复内容不能为空");
            }
            message.setResponseTime(LocalDateTime.now());
            message.setResponseName(user.getUserName());
            messageService.updateById(message);
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常,请稍后重试");
        }
    }
}
