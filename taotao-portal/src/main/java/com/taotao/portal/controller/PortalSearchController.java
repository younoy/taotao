package com.taotao.portal.controller;

import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.PortalSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 上午11:58 on 17-12-14.
 * @Modified By:
 */

@Controller
public class PortalSearchController {

    @Autowired
    private PortalSearchService portalSearchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q")String queryString, @RequestParam(defaultValue = "1")Integer page, Model model){

        if(queryString != null){
            try {
                queryString = new String(queryString.getBytes("iso8859-1"),"utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        SearchResult searchResult = portalSearchService.search(queryString,page);

        model.addAttribute("query",queryString);
        model.addAttribute("totalpage",searchResult.getPageCount());
        model.addAttribute("itemList",searchResult.getItemList());
        model.addAttribute("page",page);

        return "search";
    }
}
