package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 上午11:11 on 17-12-14.
 * @Modified By:
 */
public interface PortalSearchService {

    SearchResult search(String queryString,int page);
}
