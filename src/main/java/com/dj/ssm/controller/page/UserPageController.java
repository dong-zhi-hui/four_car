package com.dj.ssm.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserPageController {

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
}
