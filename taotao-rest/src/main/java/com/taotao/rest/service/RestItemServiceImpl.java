package com.taotao.rest.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.dao.TbItemDescMapper;
import com.taotao.dao.TbItemMapper;
import com.taotao.dao.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.rest.dao.JedisClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午1:19 on 17-12-14.
 * @Modified By:
 */

@Service
public class RestItemServiceImpl implements RestItemService{

    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Autowired
    private JedisClient jedisClient;

    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${REDIS_ITEM_EXPIRE}")
    private Integer REDIS_ITEM_EXPIRE;

    @Override
    public TaotaoResult getItemBaseInfo(long itemId) {

        try {

            //提取缓存
            String itemInfo = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId +":base");
            if(!StringUtils.isBlank(itemInfo)){
                TbItem redisItem = JsonUtils.jsonToPojo(itemInfo,TbItem.class);
                return TaotaoResult.ok(redisItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //根据商品id查询商品信息
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        //存储缓存
        try {

            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId +":base",JsonUtils.objectToJson(item));
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId +":base",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }
        //使用TaotaoResult包装一下
        return TaotaoResult.ok(item);
    }

    @Override
    public TaotaoResult getItemBaseDesc(long itemId) {
        try {

            //提取缓存
            String itemInfo = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId +":desc");
            if(!StringUtils.isBlank(itemInfo)){
                TbItemDesc redisItemDesc = JsonUtils.jsonToPojo(itemInfo,TbItemDesc.class);
                return TaotaoResult.ok(redisItemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //根据商品id查询商品信息
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        //存储缓存
        try {

            jedisClient.set(REDIS_ITEM_KEY + ":" + itemId +":desc",JsonUtils.objectToJson(itemDesc));
            jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId +":desc",REDIS_ITEM_EXPIRE);
        }catch (Exception e){
            e.printStackTrace();
        }
        //使用TaotaoResult包装一下
        return TaotaoResult.ok(itemDesc);
    }

    @Override
    public TaotaoResult getItemParam(long itemId) {
        try {
            //提取缓存
            String itemInfo = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId +":param");
            if(!StringUtils.isBlank(itemInfo)){
                TbItemParamItem redisTbItemParamItem = JsonUtils.jsonToPojo(itemInfo,TbItemParamItem.class);
                return TaotaoResult.ok(redisTbItemParamItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria  criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        if(list != null && list.size() > 0){
            TbItemParamItem paramItem = list.get(0);
            //存储缓存
            try {

                jedisClient.set(REDIS_ITEM_KEY + ":" + itemId +":param",JsonUtils.objectToJson(paramItem));
                jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId +":param",REDIS_ITEM_EXPIRE);
            }catch (Exception e){
                e.printStackTrace();
            }
            return TaotaoResult.ok(paramItem);
        }
        return TaotaoResult.build(400,"无此商品规格");
    }
}
