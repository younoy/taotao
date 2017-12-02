package com.taotao.controller;

import com.taotao.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午6:05 on 17-12-2.
 * @Modified By:
 */

@Controller
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    @RequestMapping("/showitem/{itemId}")
    @ResponseBody
    public String showItemParam(@PathVariable long itemId,Model model){
        String result = itemParamItemService.getItemParamByItemId(itemId);
        model.addAttribute("msg",result);
        return "item";
    }
}
