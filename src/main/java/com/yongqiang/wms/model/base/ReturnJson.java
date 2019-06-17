package com.yongqiang.wms.model.base;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.yongqiang.wms.common.enums.CodeEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yantao.chen on 2018/10/24.
 * 返回用与包装结果的实体类
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "message",
        "data"
})
public class ReturnJson {

    public ReturnJson(CodeEnum codeEnum) {
        this.code = codeEnum.getKey();
        this.message = codeEnum.getValue();
    }

    public ReturnJson() {
        this(CodeEnum.Ok);
    }

    public ReturnJson(CodeEnum codeEnum, Object data) {
        this(codeEnum);
        this.data = data;
    }

    public ReturnJson(CodeEnum codeEnum, List<String> messages) {
        this(codeEnum);
        this.message = messages;
    }

    public ReturnJson(Object data) {
        this(CodeEnum.Ok, data);
    }

    public ReturnJson(Integer code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

    public ReturnJson(Integer code, String message) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(null);
    }

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private Object message;
    @JsonProperty("data")
    private Object data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("message")
    public Object getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(Object message) {
        this.message = message;
    }

    @JsonProperty("data")
    public Object getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Object data) {
        this.data = data;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}