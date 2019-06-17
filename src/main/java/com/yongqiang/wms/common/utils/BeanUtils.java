package com.yongqiang.wms.common.utils;

import com.yongqiang.wms.model.base.Pair;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BeanUtils extends org.springframework.beans.BeanUtils {

    public static Object getProperty(Object object, String propertyName) {
        try {
            return getPropertyDescriptor(object.getClass(), propertyName).getReadMethod().invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

    public static void setProperty(Object object, String propertyName, Object value) {
        Method method = getPropertyDescriptor(object.getClass(), propertyName).getWriteMethod();
        try {
            method.invoke(object, value);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static <T, E> E copyProperties(T source, Class<E> clazz) {
        E object;
        try {
            object = clazz.newInstance();
        } catch (Exception e) {
            return null;
        }
        copyProperties(source, object);
        return object;
    }

    public static <T, E> List<E> copyProperties(List<T> source, Class<E> clazz) {
        return copyProperties(source, clazz, (List<Pair<String, String>>) null);
    }

    public static <T, E> List<E> copyProperties(List<T> source, Class<E> clazz, List<Pair<String, String>> mapConfig) {
        List<E> result = new ArrayList<>();
        for (T t : source) {
            E object = copyProperties(t, clazz);
            result.add(object);
            if (mapConfig == null)
                continue;
            for (Pair<String, String> pair : mapConfig) {
                Object value = getProperty(t, pair.getFirst());
                setProperty(object, pair.getSecond(), value);
            }
        }
        return result;
    }

    /**
     * 可匹配不同的属性类型
     * @param source
     * @param target
     * @throws BeansException
     */
    public static void copyPropertiesForChangeType(Object source, Object target) throws BeansException {
        copyPropertiesForChangeType(source, target, (Class)null, (String[])null);
    }

    private static void copyPropertiesForChangeType(Object source, Object target, Class<?> editable, String... ignoreProperties) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class actualEditable = target.getClass();
        if(editable != null) {
            if(!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
            }

            actualEditable = editable;
        }

        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List ignoreList = ignoreProperties != null? Arrays.asList(ignoreProperties):null;
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            PropertyDescriptor targetPd = var7[var9];
            Method writeMethod = targetPd.getWriteMethod();
            if(writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if(sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if(readMethod != null && (ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())
                            || isCovertType(writeMethod.getParameterTypes()[0], readMethod.getReturnType()))) {
                        try {
                            if(!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }

                            Object ex = readMethod.invoke(source, new Object[0]);
                            if(!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }

                            //支持int->String,String->int.long->String,String->long转.
                            if (null != ex && !ex.getClass().getName().equals(targetPd.getPropertyType().getName())) {
                                if (ex.getClass().getName().equals("java.lang.String") && targetPd.getPropertyType().getName().equals("java.lang.Integer")) {
                                    ex = Integer.parseInt(ex.toString());
                                } else if (ex.getClass().getName().equals("java.lang.Integer") && targetPd.getPropertyType().getName().equals("java.lang.String")) {
                                    ex = ex.toString();
                                } else if (ex.getClass().getName().equals("java.lang.String") && targetPd.getPropertyType().getName().equals("java.lang.Double")) {
                                    ex = Double.parseDouble(ex.toString());
                                } else if (ex.getClass().getName().equals("java.lang.Double") && targetPd.getPropertyType().getName().equals("java.lang.String")) {
                                    ex = ex.toString();
                                }
                            }

                            writeMethod.invoke(target, new Object[]{ex});
                        } catch (Exception var15) {
                            throw new FatalBeanException("Could not copy property \'" + targetPd.getName() + "\' from source to target", var15);
                        }
                    }
                }
            }
        }
    }

    public static boolean isCovertType(Class<?> lhsType, Class<?> rhsType) {
        if (lhsType.getName().equals("java.lang.Integer") && rhsType.getName().equals("java.lang.String")) {
            return true;
        } else if (lhsType.getName().equals("java.lang.String") && rhsType.getName().equals("java.lang.Integer")) {
            return true;
        } else if (lhsType.getName().equals("java.lang.String") && rhsType.getName().equals("java.lang.Double")) {
            return true;
        } else if (lhsType.getName().equals("java.lang.Double") && rhsType.getName().equals("java.lang.String")) {
            return true;
        } else {
            return false;
        }

    }
}
