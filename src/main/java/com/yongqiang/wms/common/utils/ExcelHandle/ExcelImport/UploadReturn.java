package com.yongqiang.wms.common.utils.ExcelHandle.ExcelImport;

import java.util.List;

/**
 * Created by yantao.chen on 2018-12-21
 * 上传返回对象
 */
public class UploadReturn<T> {
    public List<T> resultList;//上传结果对象集合

    public List<UploadError> errorMsgList;//上传错误信息对象集合 错误精确到excel单元格

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public List<UploadError> getErrorMsgList() {
        return errorMsgList;
    }

    public void setErrorMsgList(List<UploadError> errorMsgList) {
        this.errorMsgList = errorMsgList;
    }
}
