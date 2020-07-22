package com.dj.ssm.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/truck/")
public class TruckPageController {


    @RequestMapping("toList")
    public String toList(){
        return "truck/list";
    }






}
