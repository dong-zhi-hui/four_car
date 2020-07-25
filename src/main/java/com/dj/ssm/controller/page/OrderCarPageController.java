package com.dj.ssm.controller.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dj.ssm.config.SystemConstant;
import com.dj.ssm.pojo.BaseData;
import com.dj.ssm.pojo.Locus;
import com.dj.ssm.pojo.OrderCar;
import com.dj.ssm.service.BaseDataService;
import com.dj.ssm.service.LocusService;
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

    @Autowired
    private LocusService locusService;

    @RequestMapping("toList")
    public String toList() {
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

    /**
     * 轨迹支付信息展示
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("toPayShow/{id}")
    public String toPayShow(Model model, @PathVariable Integer id) {
        QueryWrapper<Locus> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_id", id);
        Locus locus = locusService.getOne(queryWrapper);
        model.addAttribute("locus", locus);
        return "order/pay_show";
    }


}
