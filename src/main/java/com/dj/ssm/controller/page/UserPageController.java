package com.dj.ssm.controller.page;

import com.dj.ssm.pojo.User;
import com.dj.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserPageController {
    @Autowired
    private UserService userService;

    /**
     * 去登陆
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "user/login";
    }

    /**
     * 去展示
     */
    @RequestMapping("toShow")
    public String toShow() {
        return "user/show";
    }

    /**
     * 去注册
     */
    @RequestMapping("toRegister")
    public String toRegister() {
        return "user/register";
    }

    /**
     * 去手机号登录
     */
    @RequestMapping("toPhoneLogin")
    public String toPhoneLogin() {
        return "user/phoneLong";
    }

    /**
     * 去修改密码
     */
    @RequestMapping("toUpdateUserPwd/{id}")
    public String toUpdateUserPwd(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("updateUserPwd", user);
        return "user/update_user_pwd";
    }

    /**
     * 去修改用户信息
     */
    @RequestMapping("toUpdateUser/{id}")
    public String toUpdateUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("updateUser", user);
        return "user/update_user";
    }
}
