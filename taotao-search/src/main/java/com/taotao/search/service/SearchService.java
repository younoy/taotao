package com.taotao.search.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrServerException;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:40 on 17-12-12.
 * @Modified By:
 */
public interface SearchService {

    SearchResult search(String queryString, int page, int rows) throws SolrServerException;
}
