package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午3:22 on 17-12-2.
 * @Modified By:
 */
public interface ItemParamService {

    TaotaoResult getItemParamByCid(long cid);
    TaotaoResult insertItemParam(TbItemParam tbItemParam);
    EUDataGridResult getItemList(int page, int rows);

}
