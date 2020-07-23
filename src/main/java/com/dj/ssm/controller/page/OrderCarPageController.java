package com.dj.ssm.controller.page;

import com.dj.ssm.config.SystemConstant;
import com.dj.ssm.pojo.BaseData;
import com.dj.ssm.pojo.OrderCar;
import com.dj.ssm.service.BaseDataService;
import com.dj.ssm.service.OrderCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zhw
 */
@RequestMapping("/order/")
@Controller
public class OrderCarPageController {

    @Autowired
    private OrderCarService orderCarService;

    @Autowired
    private BaseDataService baseDataService;

    @RequestMapping("toList")
    public String toList(){
        return "order/list";
    }

    @RequestMapping("toUpd/{id}")
    public String toUpd(Model model, @PathVariable Integer id) throws Exception {
        OrderCar orderCar = orderCarService.getById(id);
        List<BaseData> payList = baseDataService.findByParentId(SystemConstant.PAY);
        model.addAttribute("payList", payList);
        model.addAttribute("orderCar", orderCar);
        return "order/pay";
    }

}
