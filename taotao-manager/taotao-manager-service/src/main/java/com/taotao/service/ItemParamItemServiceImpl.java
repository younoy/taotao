package com.taotao.service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.dao.TbItemParamItemMapper;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午5:47 on 17-12-2.
 * @Modified By:
 */

@Service
public class ItemParamItemServiceImpl implements ItemParamItemService{

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public String getItemParamByItemId(long itemId) {

        TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = tbItemParamItemExample.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
        if(list == null && list.size() == 0){
            return "";
        }
        String paramData =  list.get(0).getParamData();
        //把json数据转换成java对象
        List<Map> paramList = JsonUtils.jsonToList(paramData, Map.class);

        StringBuffer sb = new StringBuffer();
        sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
        sb.append("    <tbody>\n");
        for (Map map : paramList) {
            sb.append("        <tr>\n");
            sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+map.get("group")+"</th>\n");
            sb.append("        </tr>\n");
            List<Map> params = (List<Map>) map.get("params");
            for (Map map2 : params) {
                sb.append("        <tr>\n");
                sb.append("            <td class=\"tdTitle\">"+map2.get("k")+"</td>\n");
                sb.append("            <td>"+map2.get("v")+"</td>\n");
                sb.append("        </tr>\n");
            }
        }
        sb.append("    </tbody>\n");
        sb.append("</table>");
        //sb.append("</div>");
        return sb.toString();

    }
}
