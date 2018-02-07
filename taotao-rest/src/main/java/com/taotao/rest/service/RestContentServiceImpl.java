package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.dao.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.dao.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:19 on 17-12-8.
 * @Modified By:
 */

@Service
public class RestContentServiceImpl implements RestContentService{

    @Autowired
    private TbContentMapper tbContentMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public List<TbContent> getContentList(Long contentCid) {

        //把缓存取内容
        try{

            String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY,contentCid+"");
            if(!StringUtils.isBlank(result)){
                List<TbContent> resultList = JsonUtils.jsonToList(result,TbContent.class);
                return resultList;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        List<TbContent> list = tbContentMapper.selectByExample(tbContentExample);

        //缓存中添加内容
        try{
            //把list转换成字符串
            String cacheString = JsonUtils.objectToJson(list);
            jedisClient.hset(INDEX_CONTENT_REDIS_KEY,contentCid+"",cacheString);

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }


    public TaotaoResult syncContent(long contentCid){

        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return TaotaoResult.ok();
    }
}
