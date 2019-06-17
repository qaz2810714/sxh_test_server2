package com.yongqiang.wms.common.utils.ExcelHandle;

/**
 * Created by yantao.chen on 2018-12-12
 * excel操作 转换策略Model
 */
public class FieldConvertModel {

    private String fieldName;

    private String dicName;

    private boolean extHandle;

    private String titleName;

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public boolean isExtHandle() {
        return extHandle;
    }

    public void setExtHandle(boolean extHandle) {
        this.extHandle = extHandle;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    @Override
    public String toString() {
        return "FieldConvertModel{" +
                "fieldName='" + fieldName + '\'' +
                ", dicName=" + dicName +
                '}';
    }
}
