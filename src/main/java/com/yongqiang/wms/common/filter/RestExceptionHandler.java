package com.yongqiang.wms.common.filter;

import com.yongqiang.wms.common.enums.CodeEnum;
import com.yongqiang.wms.model.base.BizException;
import com.yongqiang.wms.model.base.ResponseJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * 全局异常捕获处理
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 接口层异常:参数校验,转换等标准的spring异常
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        String uuid = MDC.get("uuid");
        logger.error("Context:" + uuid + " Global catch exception log: ", ex);
        return ResponseEntity.ok((Object) new ResponseJson(2001,ex.getMessage()));
    }
 
    @ExceptionHandler({
            BizException.class
    })
    public ResponseEntity<ResponseJson> handleThrowable(BizException e, WebRequest request) {

        CodeEnum c = e.getCodeEnum();
        ResponseJson responseJson = new ResponseJson(c.getKey(), e.getMessage());
        responseJson.setData(e.getData());
        return ResponseEntity.ok(responseJson);
    }

    /**
     * 其它异常:未处理的其它异常
     */
    @ExceptionHandler({
            Throwable.class
    })
    public final ResponseEntity<ResponseJson> handleThrowable(Throwable ex, WebRequest request) {
        String uuid = MDC.get("uuid");
        logger.error("Context:" + uuid + " Global catch exception log: ", ex);
        String msg=ex.getMessage();
        if(msg!=null&&msg.indexOf("MySQLSyntaxErrorException")!=-1){
        	msg="sql解析异常";
        }
        return ResponseEntity.ok(new ResponseJson(2001, msg));
    }
}
