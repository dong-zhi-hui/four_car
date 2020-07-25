package com.dj.ssm.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/index/")
public class IndexPageController {

    @RequestMapping("toIndex")
    public String toIndex() {
        return "index/index";
    }

    @RequestMapping("toLeft")
    public String toLeft() {
        return "index/left";
    }

    @RequestMapping("toRight")
    public String toRight() {
        return "index/right";
    }

    @RequestMapping("toTop")
    public String toTop() {
        return "index/top";
    }

    @RequestMapping("toEsc")
    public String toEsc(HttpSession session) {
        session.invalidate();
        return "user/login";
    }


}
