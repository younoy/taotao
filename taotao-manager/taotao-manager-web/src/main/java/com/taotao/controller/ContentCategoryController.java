package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午5:13 on 17-12-7.
 * @Modified By:
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCategoryList(@RequestParam(value="id", defaultValue="0")Long parentId){

        List<EUTreeNode> euTreeNodes= contentCategoryService.getContentCategoryList(parentId);
        return euTreeNodes;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult insertContentCategory(Long parentId, String name){

        TaotaoResult taotaoResult= contentCategoryService.insertContentCategory(parentId,name);
        return taotaoResult;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteContentCategory(long id){

        TaotaoResult taotaoResult= contentCategoryService.deleteContentCategory(id);
        return taotaoResult;
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateContentCategory(Long id, String name){

        TaotaoResult taotaoResult= contentCategoryService.updateContentCategory(id,name);
        return taotaoResult;
    }
}
