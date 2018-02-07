package com.taotao.portal.controller;

import com.taotao.portal.service.PortalContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午8:11 on 17-12-2.
 * @Modified By:
 */

@Controller
public class IndexController {

    @Autowired
    private PortalContentService portalContentService;

    @RequestMapping("/index")
    public String showIndex(Model model){

        String json = portalContentService.getContentList();
        model.addAttribute("ad1",json);
        return "index";
    }
}
