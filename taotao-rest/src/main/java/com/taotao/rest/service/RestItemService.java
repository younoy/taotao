package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午1:19 on 17-12-14.
 * @Modified By:
 */
public interface RestItemService {

    TaotaoResult getItemBaseInfo(long itemId);
    TaotaoResult getItemBaseDesc(long itemId);
    TaotaoResult getItemParam(long itemId);
}
