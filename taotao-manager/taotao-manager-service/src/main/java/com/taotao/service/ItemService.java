package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:40 on 17-11-25.
 * @Modified By:
 */
public interface ItemService {

    TbItem getItemById(long itemId);

    EUDataGridResult getItemList(int page,int rows);

    TaotaoResult createItem(TbItem item,String desc);
}
