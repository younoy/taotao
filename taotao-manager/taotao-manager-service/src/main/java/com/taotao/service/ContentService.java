package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午12:41 on 17-12-8.
 * @Modified By:
 */
public interface ContentService {

    EUDataGridResult getContentList(Long categoryId,int page, int rows);
    TaotaoResult insertContent(TbContent tbContent);
}
