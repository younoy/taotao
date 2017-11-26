package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午7:52 on 17-11-25.
 * @Modified By:
 */

@Controller
public class ItemCatController {

    @Autowired
    private  ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EUTreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") Long praentId) {

        List<EUTreeNode> result = itemCatService.getItemCatList(praentId);
        return result;
    }
}
