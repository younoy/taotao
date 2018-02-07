package com.taotao.rest.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.RestItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:38 on 17-12-7.
 * @Modified By:
 */

@Controller
public class ItemCatController {

    @Autowired
    private RestItemCatService itemCatService;

    @RequestMapping(value = "/itemcat/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback){

        String result = null;
        try {

            CatResult catResult = itemCatService.queryAllCategory();
            String json = JsonUtils.objectToJson(catResult);
            result = callback+"("+json+");";

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
