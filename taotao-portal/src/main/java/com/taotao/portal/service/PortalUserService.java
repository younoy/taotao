package com.taotao.portal.service;

import com.taotao.pojo.TbUser;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午1:50 on 17-12-16.
 * @Modified By:
 */
public interface PortalUserService {

    TbUser getUser(String token);
}
