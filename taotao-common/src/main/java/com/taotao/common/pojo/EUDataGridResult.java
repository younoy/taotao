package com.taotao.common.pojo;

import java.util.List;

/**
 * @Author: upsmart
 * @Description:
 * @Date: Created by 下午7:06 on 17-11-25.
 * @Modified By:
 */
public class EUDataGridResult {

    private long total;
    private List<?> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
