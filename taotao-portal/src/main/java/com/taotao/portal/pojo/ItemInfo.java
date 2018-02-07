package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午3:46 on 17-12-14.
 * @Modified By:
 */
public class ItemInfo extends TbItem{

    public String[] getImages() {
        String image = getImage();
        if (image != null) {
            String[] images = image.split(",");
            return images;
        }
        return null;
    }
}
