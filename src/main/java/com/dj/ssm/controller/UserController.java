package com.dj.ssm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dj.ssm.config.ResultModel;
import com.dj.ssm.config.SendMailUtils;
import com.dj.ssm.pojo.User;
import com.dj.ssm.pojo.UserQuery;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
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

    /**
     * 用户展示
     * @param userQuery
     * @param user
     * @return
     */
    @RequestMapping("show")
    public ResultModel show(UserQuery userQuery, @SessionAttribute("user") User user){
        try {
            Map<String,Object> map = new HashMap<>();
            //分页
            Page<User> page = new Page<>(userQuery.getPageNo(), userQuery.getPageNoSize());
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            //用户名查询
            if(StringUtils.hasText(userQuery.getUserName())){
                queryWrapper.eq("user_name", userQuery.getUserName());
            }
            //条件限定
            if(user.getLevel() != 3){
                queryWrapper.eq("id", user.getId());
            }
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

    /**
     * 姓名  手机号  车牌号  查重
     * @param user
     * @return
     */
    @RequestMapping("findUserNameOrPhoneOrPlateNumber")
    public Boolean findUserNameOrPhoneOrPlateNumber(User user){

        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            if(StringUtils.hasText(user.getUserName())){
                queryWrapper.eq("user_name", user.getUserName());
            }
            if(StringUtils.hasText(user.getPhone())){
                queryWrapper.eq("phone", user.getPhone());
            }
            if(StringUtils.hasText(user.getPlateNumber())){
                queryWrapper.eq("plate_number", user.getPlateNumber());
            }
            User u = userService.getOne(queryWrapper);
            return u == null ? true : false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping("register")
    public ResultModel register(User user, HttpServletRequest request){
        try {
            user.setCreateTime(LocalDateTime.now());
            userService.saveUser(user);
            SendMailUtils.sendEmail("1599814565@qq.com", "用户激活", "<a href='http://127.0.0.1:8080/" +
                    "user/updateStatus?id=" + user.getId() + "&userStatus=1'>点此激活</a>");
            SendMailUtils.sendEmail("1599814565@qq.com", "注册成功", "<h1>恭喜您！注册成功！</h1>");
            return new ResultModel().success();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultModel().error("服务器异常");
        }

    }

}
