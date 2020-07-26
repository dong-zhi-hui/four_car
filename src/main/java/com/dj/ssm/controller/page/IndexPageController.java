package com.dj.ssm.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/index/")
public class IndexPageController {

    @RequestMapping("toIndex")
    public String toIndex() {
        return "index/index";
    }

    @RequestMapping("toEsc")
    public String toEsc(HttpSession session) {
        session.invalidate();
        return "user/login";
    }


}
