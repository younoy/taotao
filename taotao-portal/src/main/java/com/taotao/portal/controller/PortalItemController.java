package com.taotao.portal.controller;

import com.taotao.pojo.TbItemParam;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.PortalItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午3:27 on 17-12-14.
 * @Modified By:
 */

@Controller
public class PortalItemController {

    @Autowired
    private PortalItemService portalItemService;

    @RequestMapping("item/{itemId}")
    public String getItem(@PathVariable Long itemId, Model model){

        ItemInfo item = portalItemService.getItemByid(itemId);
        model.addAttribute("item",item);
        return "item";
    }

    @RequestMapping(value="/item/desc/{itemId}", produces= MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemDesc(@PathVariable Long itemId){

        String result = portalItemService.getItemDescById(itemId);
        return result;
    }

    @RequestMapping(value="/item/param/{itemId}", produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemParam(@PathVariable Long itemId) {
        String string = portalItemService.getItemParamById(itemId);
        return string;
    }
}
