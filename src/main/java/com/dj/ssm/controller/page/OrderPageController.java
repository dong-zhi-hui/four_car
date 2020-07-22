package com.dj.ssm.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhw
 */
@RequestMapping("/order/")
@Controller
public class OrderPageController {

    @RequestMapping("toList")
    public String toList(){
        return "order/list";
    }


}
