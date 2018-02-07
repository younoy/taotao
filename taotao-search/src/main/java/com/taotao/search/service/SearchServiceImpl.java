package com.taotao.search.service;

import com.taotao.search.pojo.Item;
import com.taotao.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:41 on 17-12-12.
 * @Modified By:
 */

@Service
public class SearchServiceImpl implements SearchService{

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult search(String queryString, int page, int rows) throws SolrServerException {

        SolrQuery solrQuery = new SolrQuery();

        solrQuery.setQuery(queryString);
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        solrQuery.set("df","item_keywords");

        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color:red\">");
        solrQuery.setHighlightSimplePost("</em>");

        SearchResult searchResult = searchByQuery(solrQuery);

        long recordCount = searchResult.getRecordCount();
        long pageCount = recordCount/rows;
        if(recordCount%rows > 0){
            pageCount++;
        }
        searchResult.setPageCount(pageCount);
        searchResult.setCurPage(page);

        return searchResult;
    }


    private SearchResult searchByQuery(SolrQuery query) throws SolrServerException {

        SearchResult searchResult = new SearchResult();

        QueryResponse queryResponse = solrServer.query(query);

        SolrDocumentList solrDocuments = queryResponse.getResults();

        searchResult.setRecordCount(solrDocuments.getNumFound());

        List<Item> itemList = new ArrayList<>();

        Map<String,Map<String,List<String>>> highting = queryResponse.getHighlighting();

        for(SolrDocument solrDocument:solrDocuments){
            Item item = new Item();
            item.setId((String) solrDocument.get("id"));
            List<String> list = highting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (list != null && list.size()>0) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));
            item.setCategory_name((String) solrDocument.get("item_category_name"));

            itemList.add(item);
        }
        searchResult.setItemList(itemList);
        return searchResult;
    }
}
