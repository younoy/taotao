package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午7:40 on 17-11-25.
 * @Modified By:
 */
public interface ItemCatService {

    List<EUTreeNode> getItemCatList(long parendId);
}
