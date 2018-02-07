package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:19 on 17-12-8.
 * @Modified By:
 */
public interface RestContentService {

    List<TbContent> getContentList(Long contentCid);
    TaotaoResult syncContent(long contentCid);

}
