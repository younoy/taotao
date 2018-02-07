package com.taotao.rest.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.pojo.TbContent;
import com.taotao.rest.service.RestContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:23 on 17-12-8.
 * @Modified By:
 */

@Controller
@RequestMapping("/content")
public class RestContentController {

    @Autowired
    private RestContentService restContentService;

    @RequestMapping("/list/{contentCid}")
    @ResponseBody
    public TaotaoResult getContentList(@PathVariable Long contentCid){

        try {
            List<TbContent> result = restContentService.getContentList(contentCid);
            return TaotaoResult.ok(result);
        }catch (Exception e){
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
