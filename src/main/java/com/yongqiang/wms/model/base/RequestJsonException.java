package com.yongqiang.wms.model.base;//package com.zhaogang.ii.saas.cloudstore.pangmao.service.model.base;
//
//
//import com.zhaogang.ii.saas.cloudstore.pangmao.service.model.enums.CodeEnum;
//
///**
// * Created by yantao.chen on 2018.10.24
// */
//public class RequestJsonException extends RuntimeException {
//
//    private CodeEnum codeEnum;
//
//    private Object data;
//
//    public RequestJsonException(String message) {
//        super(message);
//        this.codeEnum = CodeEnum.ServerBizError;
//    }
//
//    public RequestJsonException(CodeEnum codeEnum, String message) {
//        super(message);
//        this.codeEnum = codeEnum;
//    }
//
//    public RequestJsonException(CodeEnum codeEnum, Object data) {
//        this.codeEnum = codeEnum;
//        this.data = data;
//    }
//
//    public RequestJsonException(CodeEnum codeEnum, String message, Object data) {
//        super(message);
//        this.codeEnum = codeEnum;
//        this.data = data;
//    }
//
//    public CodeEnum getCodeEnum() {
//        return codeEnum;
//    }
//
//    public void setCodeEnum(CodeEnum codeEnum) {
//        this.codeEnum = codeEnum;
//    }
//
//    public Object getData() {
//        return data;
//    }
//
//    public void setData(Object data) {
//        this.data = data;
//    }
//}
