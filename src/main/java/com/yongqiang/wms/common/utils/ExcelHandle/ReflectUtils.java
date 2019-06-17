package com.yongqiang.wms.common.utils.ExcelHandle;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yantao.chen on 2018-12-20
 */
public class ReflectUtils {
    /**
     * 获得包括父类的所有属性field
     * @param bizClass
     * @return
     */
    public static Field[] getAllFields(Class bizClass){
        List<Field> fieldList = new ArrayList<>();
        while (bizClass != null){
            fieldList.addAll(new ArrayList<>(Arrays.asList(bizClass.getDeclaredFields())));
            bizClass = bizClass.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    /**
     * 获取属性field(包括父类)
     * tryCount父类找寻深度
     * @return
     */
    public static Field getField(Class bizClass,String fieldName,int tryCount) {
        Field resultField;
        if(tryCount <= 0){
            return null;
        }
        try {
            resultField = bizClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            tryCount -= 1;
            resultField = getField(bizClass.getSuperclass(),fieldName,tryCount);
        }
        return resultField;
    }
}
