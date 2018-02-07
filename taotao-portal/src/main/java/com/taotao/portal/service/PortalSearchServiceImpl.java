package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.portal.pojo.SearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 上午11:11 on 17-12-14.
 * @Modified By:
 */

@Service
public class PortalSearchServiceImpl implements PortalSearchService{

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String queryString, int page) {

        Map<String, String> param = new HashMap<>();
        param.put("q",queryString);
        param.put("page",page+"");

        String queryResult = HttpClientUtil.doGet(SEARCH_BASE_URL,param);

        try {
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(queryResult, SearchResult.class);
            if (taotaoResult.getStatus() == 200) {
                SearchResult result = (SearchResult) taotaoResult.getData();
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
