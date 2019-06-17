package com.yongqiang.wms.common.utils.ExcelHandle.ExcelImport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * excel导入操作列title和字段对应
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelTitleField {
    public String title() default "";
    public String dicName() default "";
    boolean extHandle() default false;
}
