package com.xnpool.scheduler.common.exception;


import com.xnpool.scheduler.common.contants.DDContant;
import com.xnpool.scheduler.common.utils.DingdingUtils;
import com.xnpool.scheduler.common.utils.ResponseResult;
import com.xnpool.scheduler.common.utils.SysInfoPrinter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常通用处理
 */
@ControllerAdvice
@Slf4j
@ResponseBody
public class DefaultExceptionAdvice {
    /**
     * 所有异常统一处理
     * 返回状态码:500
     */
    @ExceptionHandler(BusinessException.class)
    public <T>ResponseResult<T> handleException(BusinessException e) {
        return new ResponseResult(ResponseResult.FALSE,e.getMessage());
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public <T>ResponseResult<T> handleException(HttpRequestMethodNotSupportedException e) {
        return new ResponseResult(ResponseResult.FALSE,"请求方式不支持");
    }
    /**
     * 所有异常统一处理
     * 返回状态码:500
     */
    @ExceptionHandler(Exception.class)
    public <T>ResponseResult<T> handleException(Exception e) {
        DingdingUtils.robot(DDContant.TYPE_500, SysInfoPrinter.getExceptionInformation(e));
        log.error(SysInfoPrinter.getExceptionInformation(e));
        return new ResponseResult(ResponseResult.FALSE,"系统异常，请联系管理员");
    }

}
