package com.taotao.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.dao.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午3:23 on 17-12-2.
 * @Modified By:
 */

@Service
public class ItemParamServiceImpl implements ItemParamService{

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public TaotaoResult getItemParamByCid(long cid) {

        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(tbItemParamExample);

        if(list != null && list.size() > 0){
            return TaotaoResult.ok(list.get(0));
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(TbItemParam tbItemParam) {
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());
        tbItemParamMapper.insert(tbItemParam);
        return TaotaoResult.ok();
    }

    @Override
    public EUDataGridResult getItemList(int page, int rows) {

        TbItemParamExample example = new TbItemParamExample();
        PageHelper.startPage(page,rows);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);

        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
