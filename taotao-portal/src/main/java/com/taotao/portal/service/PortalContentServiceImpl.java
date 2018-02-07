package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午4:06 on 17-12-8.
 * @Modified By:
 */

@Service
public class PortalContentServiceImpl implements PortalContentService{

    @Value("${REST_BASE_URL}")
    private  String REST_BASE_URL;
    @Value("${REST_INDEX_AD_URL}")
    private  String REST_INDEX_AD_URL;

    @Override
    public String getContentList() {

        String result = HttpClientUtil.doGet(REST_BASE_URL + REST_INDEX_AD_URL);

        try {

            TaotaoResult taotaoResult = TaotaoResult.formatToList(result, TbContent.class);
            List<TbContent> list = (List<TbContent>) taotaoResult.getData();

            List<Map> resultList = new ArrayList<>();

            for(TbContent tbContent:list){
                Map map = new HashMap();
                map.put("src",tbContent.getPic());
                map.put("height", 240);
                map.put("width", 670);
                map.put("srcB", tbContent.getPic2());
                map.put("widthB", 550);
                map.put("heightB", 240);
                map.put("href", tbContent.getUrl());
                map.put("alt", tbContent.getSubTitle());
                resultList.add(map);
            }

            return JsonUtils.objectToJson(resultList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
