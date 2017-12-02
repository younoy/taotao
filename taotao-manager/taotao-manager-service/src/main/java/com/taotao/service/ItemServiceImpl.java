package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.dao.TbItemDescMapper;
import com.taotao.dao.TbItemMapper;
import com.taotao.dao.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午2:41 on 17-11-25.
 * @Modified By:
 */

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItem getItemById(long itemId) {

        TbItemExample tbItemExample = new TbItemExample();
        TbItemExample.Criteria criteria = tbItemExample.createCriteria();

        criteria.andIdEqualTo(itemId);
        List<TbItem> list = tbItemMapper.selectByExample(tbItemExample);
        if(list != null && list.size() >0){
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {

        TbItemExample example = new TbItemExample();
        PageHelper.startPage(page,rows);
        List<TbItem> list = tbItemMapper.selectByExample(example);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Transactional
    @Override
    public TaotaoResult createItem(TbItem item,String desc,String itemParams) throws Exception {
        //item补全

        //商品id
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        //商品状态
        item.setStatus((byte) 1);
        //商品的时间
        item.setCreated(new Date());
        item.setUpdated(new Date());

        tbItemMapper.insert(item);
        TaotaoResult taotaoResult = insertItemDesc(itemId,desc);
        TaotaoResult taotaoResult1 = insertItemParam(itemId,itemParams);

        if(taotaoResult.getStatus() !=200 | taotaoResult1.getStatus() !=200){
            throw new Exception();
        }
        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemDesc(Long itemId,String desc){
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        tbItemDescMapper.insert(tbItemDesc);
        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemParam(Long itemId,String itemParams){
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setCreated(new Date());
        tbItemParamItem.setUpdated(new Date());
        tbItemParamItemMapper.insert(tbItemParamItem);
        return TaotaoResult.ok();
    }
}
