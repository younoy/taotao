package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午3:19 on 17-12-14.
 * @Modified By:
 */
public interface PortalItemService {

    ItemInfo getItemByid(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParamById(Long itemId);
}
