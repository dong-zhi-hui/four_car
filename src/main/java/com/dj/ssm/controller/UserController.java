package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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
    public ResultModel login(User user, Model model, HttpSession session){
        try {
            if(user.getUserName().isEmpty() || user.getUserPwd().isEmpty()){
                return new ResultModel().success("账号或密码不能为空");
            }
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name",user.getUserName());
            queryWrapper.eq("user_pwd",user.getUserPwd());
            User u = userService.getOne(queryWrapper);
            if(null == u){
                return new ResultModel().success("账号或密码错误");
            }
            session.setAttribute("user", u);
            model.addAttribute("user",u);
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().success("服务器异常");
        }

    }
}
