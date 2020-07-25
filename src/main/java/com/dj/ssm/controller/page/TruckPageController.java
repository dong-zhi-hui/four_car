package com.dj.ssm.controller.page;

import com.dj.ssm.pojo.TruckSpace;
import com.dj.ssm.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhw
 */
@Controller
@RequestMapping("/truck/")
public class TruckPageController {

    @Autowired
    private TruckService truckService;

    @RequestMapping("toList")
    public String toList() {
        return "truck/list";
    }

    @RequestMapping("toUpdate/{id}")
    public String toUpdate(@PathVariable Integer id, Model model) {
        TruckSpace truckSpace = truckService.getById(id);
        model.addAttribute("truckSpace", truckSpace);
        return "truck/update";
    }

    @RequestMapping("toAdd")
    public String toAdd() {
        return "truck/add";
    }

    /**
     * echarts
     *
     * @return
     */
    @RequestMapping("echarts")
    public String echarts() {
        return "truck/echarts";
    }


}
