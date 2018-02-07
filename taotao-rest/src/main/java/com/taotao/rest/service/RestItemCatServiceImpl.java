package com.taotao.rest.service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.dao.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.dao.JedisClient;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:15 on 17-12-7.
 * @Modified By:
 */

@Service
public class RestItemCatServiceImpl implements RestItemCatService{

    @Autowired
    private TbItemCatMapper tbItemCatMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${INDEX_CONTENT_CATEGORY_REDIS_KEY}")
    private String INDEX_CONTENT_CATEGORY_REDIS_KEY;

    @Override
    public CatResult queryAllCategory() throws Exception {

        CatResult catResult = new CatResult();
        //从redis中取出数据
        try{

            String categoryResult = jedisClient.get(INDEX_CONTENT_CATEGORY_REDIS_KEY);
            if(!StringUtils.isBlank(categoryResult)) {
                List categoryNodes = JsonUtils.jsonToList(categoryResult, CatNode.class);
                catResult.setData(categoryNodes);
                return catResult;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        catResult.setData(getCatList(0));

        //存储到redis中
        try{

            String redisResult = JsonUtils.objectToJson(getCatList(0));
            jedisClient.set(INDEX_CONTENT_CATEGORY_REDIS_KEY,redisResult);
        }catch (Exception e){
            e.printStackTrace();
        }
        return catResult;
    }


    private List<?> getCatList(long parentId){

        TbItemCatExample tbItemCatExample = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = tbItemCatExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = tbItemCatMapper.selectByExample(tbItemCatExample);

        List catNodes = new ArrayList<>();
        int count = 0;
        for(TbItemCat tbItemCat:tbItemCats){
            CatNode catNode = new CatNode();

            if(tbItemCat.getIsParent()) {
                if (parentId == 0)
                    catNode.setName("<a href='/products/" + tbItemCat.getId() + ".html'>" + tbItemCat.getName() + "</a>");
                else
                    catNode.setName(tbItemCat.getName());
                catNode.setUrl("/products/" + tbItemCat.getId() + ".html");
                catNode.setItem(getCatList(tbItemCat.getId()));
                catNodes.add(catNode);
                count ++;
                if(parentId == 0 && count>=14)
                    break;
            }else{
                catNodes.add("/products/" + tbItemCat.getId() + ".html|"+tbItemCat.getName());
            }
        }
        return catNodes;
    }
}
