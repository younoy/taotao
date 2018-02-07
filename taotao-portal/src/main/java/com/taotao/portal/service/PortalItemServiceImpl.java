package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午3:19 on 17-12-14.
 * @Modified By:
 */

@Service
public class PortalItemServiceImpl implements PortalItemService{

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${ITME_INFO_URL}")
    private String ITME_INFO_URL;
    @Value("${ITME_DESC_URL}")
    private String ITME_DESC_URL;
    @Value("${ITME_PARAM_URL}")
    private String ITME_PARAM_URL;

    public ItemInfo getItemByid(Long itemId){

        try{

            String json = HttpClientUtil.doGet(REST_BASE_URL+ITME_INFO_URL+itemId);
            if(!StringUtils.isBlank(json)){
                TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json,ItemInfo.class);
                if(taotaoResult.getStatus() == 200){
                    ItemInfo tbItem = (ItemInfo) taotaoResult.getData();
                    return tbItem;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String getItemDescById(Long itemId) {
        try {
            //查询商品描述
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITME_DESC_URL + itemId);
            //转换成java对象
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
            if (taotaoResult.getStatus() == 200) {
                TbItemDesc itemDesc = (TbItemDesc) taotaoResult.getData();
                //取商品描述信息
                String result = itemDesc.getItemDesc();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getItemParamById(Long itemId) {
        try {
            //查询商品描述
            String json = HttpClientUtil.doGet(REST_BASE_URL + ITME_PARAM_URL + itemId);
            //转换成java对象
            TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemParamItem.class);
            if (taotaoResult.getStatus() == 200) {
                TbItemParamItem itemParamItem = (TbItemParamItem) taotaoResult.getData();
                //取商品描述信息
                String result = itemParamItem.getParamData();
                //生成html
                // 把规格参数json数据转换成java对象
                List<Map> jsonList = JsonUtils.jsonToList(result, Map.class);
                StringBuffer sb = new StringBuffer();
                sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
                sb.append("    <tbody>\n");
                for(Map m1:jsonList) {
                    sb.append("<tr>\n");
                    sb.append("<th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
                    sb.append("</tr>\n");
                    List<Map> list2 = (List<Map>) m1.get("params");
                    for(Map m2:list2) {
                        sb.append("<tr>\n");
                        sb.append("<td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
                        sb.append("<td>"+m2.get("v")+"</td>\n");
                        sb.append("</tr>\n");
                    }
                }
                sb.append("    </tbody>\n");
                sb.append("</table>");
                //返回html片段
                return sb.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
