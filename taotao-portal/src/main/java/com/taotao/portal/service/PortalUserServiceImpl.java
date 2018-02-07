package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午1:50 on 17-12-16.
 * @Modified By:
 */

@Service
public class PortalUserServiceImpl implements PortalUserService{

    @Value("${SSO_BASE_URL}")
    public String SSO_BASE_URL;
    @Value("${SSO_TOKEN_URL}")
    public String SSO_TOKEN_URL;
    @Value("${SSO_LOGIN_URL}")
    public String SSO_LOGIN_URL;

    @Override
    public TbUser getUser(String token) {

        String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_TOKEN_URL + token);
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbUser.class);
        if(taotaoResult.getStatus() == 200){
            TbUser user = (TbUser) taotaoResult.getData();
            return user;
        }
        return null;
    }
}
