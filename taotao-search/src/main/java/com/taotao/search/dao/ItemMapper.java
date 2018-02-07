package com.taotao.search.dao;

import com.taotao.search.pojo.Item;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午1:42 on 17-12-12.
 * @Modified By:
 */
public interface ItemMapper {

    List<Item> getItemList();
}
