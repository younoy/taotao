package com.taotao.controller;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午3:28 on 17-12-2.
 * @Modified By:
 */

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;


    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult queryItemParam(int page,int rows){
        EUDataGridResult result = itemParamService.getItemList(page,rows);
        return result;
    }

    @RequestMapping("/query/itemcatid/{itemCatid}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable long itemCatid){
        TaotaoResult result = itemParamService.getItemParamByCid(itemCatid);
        return result;
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable long cid,String paramData){

        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(cid);
        tbItemParam.setParamData(paramData);
        TaotaoResult result = itemParamService.insertItemParam(tbItemParam);
        return result;
    }
}
