package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.dao.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午7:40 on 17-11-25.
 * @Modified By:
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EUTreeNode> getItemCatList(long parendId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(parendId);
        List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
        List<EUTreeNode> result = new ArrayList<>();
        for (TbItemCat tbItemCat:list) {
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setId(tbItemCat.getId());
            euTreeNode.setText(tbItemCat.getName());
            euTreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            result.add(euTreeNode);
        }
        return result;
    }
}
