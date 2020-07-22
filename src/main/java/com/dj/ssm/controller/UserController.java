package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.pojo.User;
import com.dj.ssm.pojo.UserQuery;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user
     * @return
     */
    @RequestMapping("login")
    public ResultModel login(User user, HttpSession session){
        try {
            if(user.getUserName().isEmpty() || user.getUserPwd().isEmpty()){
                return new ResultModel().success("账号或密码不能为空");
            }
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name", user.getUserName());
            queryWrapper.eq("user_pwd", user.getUserPwd());
            User u = userService.getOne(queryWrapper);
            if(null == u){
                return new ResultModel().error("账号或密码错误");
            }
            session.setAttribute("user", u);
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常");
        }

    }

    @RequestMapping("show")
    public ResultModel show(UserQuery userQuery){
        try {
            Map<String,Object> map = new HashMap<>();
            //分页
            Page<User> page = new Page<>(userQuery.getPageNo(), userQuery.getPageNoSize());
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            IPage<User> pageInfo = userService.page(page,queryWrapper);
            map.put("user",pageInfo.getRecords());
            map.put("pages", pageInfo.getPages());
           // List<User> user = userService.list();
            return new ResultModel().success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常");
        }
    }
}
