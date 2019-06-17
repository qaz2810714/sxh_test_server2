package com.yongqiang.wms.model.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * Created by yantao.chen on 2019-05-28
 */
public class TechDto extends Technology{
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
