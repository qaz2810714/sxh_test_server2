package com.yongqiang.wms.model;


import com.yongqiang.wms.model.base.AbstractBaseModel;

public class BaseDictionary extends AbstractBaseModel {
    /**
     *主键ID
     */
    private Integer id;

    /**
     *账套id
     */
    private Integer accountId;

    /**
     *字典分类编码
     */
    private String classCode;

    /**
     *字典分类名称
     */
    private String className;

    /**
     *字典项编码
     */
    private String itemCode;

    /**
     *字典项名称
     */
    private String itemName;

    /**
     *字典项值
     */
    private String itemValue;

    /**
     *备注
     */
    private String remark;

    /**
     *字典类型( 1表示为不显示 2表示为显示不可编辑 3表示显示可编辑)
     */
    private Integer type;

    /**
     *是否有效(1有效)
     */
    private Boolean valid;


    /**
     *排序号码
     */
    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode == null ? null : classCode.trim();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemValue() {
        return itemValue;
    }

    public void setItemValue(String itemValue) {
        this.itemValue = itemValue == null ? null : itemValue.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }


    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}