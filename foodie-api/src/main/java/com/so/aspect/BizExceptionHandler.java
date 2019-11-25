package com.so.aspect;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.so.utils.ServerResponseResult;

/**
 * 应用模块名称： Valid参数校验异常处理类
 * <p>
 * 
 * @author show
 * @since 2019/10/28 20:12
 */
@RestControllerAdvice
public class BizExceptionHandler {
    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     * 
     * @author show
     * @date 2019/10/28 20:40
     * @param e
     *            错误对象
     * @return cn.csg.lib.web.response.MsAppResponse
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ServerResponseResult bindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return ServerResponseResult.errorMsg(message);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     * 
     * @author show
     * @date 2019/10/28 20:40
     * @param e
     *            错误对象
     * @return cn.csg.lib.web.response.MsAppResponse
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ServerResponseResult constraintViolationExceptionHandler(ConstraintViolationException e) {
        String message =
            e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return ServerResponseResult.errorMsg(message);
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     * 
     * @author show
     * @date 2019/10/28 20:40
     * @param e
     *            错误对象
     * @return cn.csg.lib.web.response.MsAppResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ServerResponseResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return ServerResponseResult.errorMsg(message);
    }
}
