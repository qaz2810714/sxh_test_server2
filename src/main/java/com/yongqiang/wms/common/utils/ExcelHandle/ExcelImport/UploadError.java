package com.yongqiang.wms.common.utils.ExcelHandle.ExcelImport;

/**
 * Created by yantao.chen on 2018-12-21
 * 上传excel解析错误封装对象
 */
public class UploadError {
    public Integer row;

    public Integer column;

    public String errorMsg;

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public UploadError(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
