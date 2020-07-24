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

    @RequestMapping("toLogin")
    public String toLogin(){
        return "user/login";
    }

    @RequestMapping("toShow")
    public String toShow(){
        return "user/show";
    }

    @RequestMapping("toRegister")
    public String toRegister(){
        return "user/register";
    }

    @RequestMapping("updateStatus")
    public String updateStatus(User user){
        userService.updateById(user);
        return "user/login";
    }

    @RequestMapping("toPhoneLogin")
    public String toPhoneLogin(){
        return "user/phoneLong";
    }

    @RequestMapping("toUpdateUserPwd/{id}")
    public String toUpdateUserPwd(@PathVariable("id") Integer id, Model model){
        User user = userService.getById(id);
        model.addAttribute("updateUserPwd", user);
        return "user/update_user_pwd";
    }

    @RequestMapping("toUpdateUser/{id}")
    public String toUpdateUser(@PathVariable("id") Integer id, Model model){
        User user = userService.getById(id);
        model.addAttribute("updateUser", user);
        return "user/update_user";
    }
}
