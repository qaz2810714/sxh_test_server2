package com.yongqiang.wms.model.stock;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Date;
import java.util.List;

/**
 * 入库Dto
 *
 * @author yang.shang
 * @create 2019-05-29 10:27
 **/
public class WmsInInfoDto extends WmsInInfo {
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    private Date createStartTime;

    private Date createEndTime;

    public Date getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(Date createStartTime) {
        this.createStartTime = createStartTime;
    }

    public Date getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(Date createEndTime) {
        this.createEndTime = createEndTime;
    }

    /**
     * 明细级数据
     */
    private List<WmsInDetail> details;

    public List<WmsInDetail> getDetails() {
        return details;
    }

    public void setDetails(List<WmsInDetail> details) {
        this.details = details;
    }

    /**
     * 修改前明细级数据
     */
    private List<WmsInDetail> preDetails;

    /**
     * 修改后明细级数据
     */
    private List<WmsInDetail> aftDetails;


    public List<WmsInDetail> getPreDetails() {
        return preDetails;
    }

    public void setPreDetails(List<WmsInDetail> preDetails) {
        this.preDetails = preDetails;
    }

    public List<WmsInDetail> getAftDetails() {
        return aftDetails;
    }

    public void setAftDetails(List<WmsInDetail> aftDetails) {
        this.aftDetails = aftDetails;
    }
}
