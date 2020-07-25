package com.dj.ssm.controller.page;

import com.dj.ssm.pojo.Message;
import com.dj.ssm.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MessagePageController
 */
@Controller
@RequestMapping("/message/")
public class MessagePageController {

    @Autowired
    private MessageService messageService;

    /**
     * 去留言板
     *
     * @return
     */
    @RequestMapping("toShow")
    public String toShow() {
        return "message/show";
    }

    /**
     * 去新增留言
     *
     * @return
     */
    @RequestMapping("toAdd")
    public String toAdd() {
        return "message/add";
    }

    /**
     * 回复
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("toUpdate")
    public String toUpdate(Integer id, Model model) {
        Message message = messageService.getById(id);
        model.addAttribute("message", message);
        return "message/update";
    }


}
