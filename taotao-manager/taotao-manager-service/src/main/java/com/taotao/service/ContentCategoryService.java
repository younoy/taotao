package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午5:07 on 17-12-7.
 * @Modified By:
 */
public interface ContentCategoryService {

    List<EUTreeNode> getContentCategoryList(long parentId);
    TaotaoResult insertContentCategory(Long parentId, String name);
    TaotaoResult deleteContentCategory(long id);
    TaotaoResult updateContentCategory(Long id, String name);
}
