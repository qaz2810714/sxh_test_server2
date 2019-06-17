package com.yongqiang.wms.model.base;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 业务对象基础类
 */
public abstract class AbstractBaseModel {
    
    /**
     * 逻辑删除标识
     */
    private Boolean deleteFlag;

    /**
     * 创建人ID
     */
	private Long creator;
    
    /**
     * 创建人名称（非持久化）
     */
    private String creatorName;
    
    /**
     * 创建时间
     */
	private Date createTime;
    
    /**
     * 更新人ID
     */
	private Long modifier;
    
    /**
     * 更新人姓名
     */
    private String modifierName;
    
    /**
     * 更新时间
     */
	private Date modifyTime;

    /**
     * 分页页码
     */
    private Integer page;

    /**
     * 每页大小
     */
    private Integer size;

    /**
     * 排序字段
     */
    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @param deleteFlag the deleteFlag to set
     */
    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * @return the creator
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * @return the createTime
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS", timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the modifier
     */
    public Long getModifier() {
        return modifier;
    }

    /**
     * @param modifier the modifier to set
     */
    public void setModifier(Long modifier) {
        this.modifier = modifier;
    }

    /**
     * @return the modifyTime
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS", timezone = "GMT+8")
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime the modifyTime to set
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return the creatorName
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * @param creatorName the creatorName to set
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * @return the modifierName
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * @param modifierName the modifierName to set
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }
    
}
