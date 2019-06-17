package com.yongqiang.wms.common.utils.ExcelHandle.ExcelExport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 导出操作注解
 */
@Retention(value = RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface ExcelExportTitle {
    int index() default -1;
    String title() default "";
    String dicName() default "";
    boolean extHandle() default false;
}
