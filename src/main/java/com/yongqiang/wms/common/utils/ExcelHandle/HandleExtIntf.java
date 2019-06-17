package com.yongqiang.wms.common.utils.ExcelHandle;

import com.yongqiang.wms.common.utils.ExcelHandle.ExcelImport.UploadError;

import java.lang.reflect.Field;

/**
 * EXCEL特殊字段处理接口
 * @author yang.shang
 * @create 2018-12-14 14:23
 **/
public interface HandleExtIntf {
    /**
     * 对特殊字段的转换处理 返回一个处理后的值 使用时必须在该字段上的注解标记 extHandle = true
     * @param field 对应的业务字段
     * @param value 单元格值
     * @return
     */
    String  handle(Field field, String value);

    /**
     * 业务校验处理
     * @param field 业务校验的字段
     * @param value 需要校验的单元格值
     * @param error 返回的error信息
     */
    void valid(Field field, String value, UploadError error);
}
