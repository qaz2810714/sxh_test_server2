package com.yongqiang.wms.common.enums;

/**
 * 
 */
public enum CodeEnum {
    Ok(100, "处理成功"),
    Error(4001, "处理失败.");
	
    private int key;

    private String value;

    CodeEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
