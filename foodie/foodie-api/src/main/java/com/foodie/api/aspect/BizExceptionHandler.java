package com.foodie.api.aspect;

import com.foodie.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * Valid参数校验异常处理类
 * @author jamie
 * @since 2019/10/28 20:12
 */
@RestControllerAdvice
@Slf4j
@Order(1)
public class BizExceptionHandler {

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     * @param e 错误对象
     * @return cn.csg.lib.web.response.MsAppResponse
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public R<String> bindExceptionHandler(BindException e) {
        String message = e.getBindingResult()
                          .getAllErrors()
                          .stream()
                          .map(DefaultMessageSourceResolvable::getDefaultMessage)
                          .collect(Collectors.joining());
        log.error("参数校验失败，失败原因为：{}", message);
        return R.errorMsg(message);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     * @param e 错误对象
     * @return cn.csg.lib.web.response.MsAppResponse
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public R<String> constraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations()
                          .stream()
                          .map(ConstraintViolation::getMessage)
                          .collect(Collectors.joining());
        log.error("参数校验失败，失败原因为：{}", message);
        return R.errorMsg(message);
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     * @param e 错误对象
     * @return cn.csg.lib.web.response.MsAppResponse
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public R<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                          .getAllErrors()
                          .stream()
                          .map(DefaultMessageSourceResolvable::getDefaultMessage)
                          .collect(Collectors.joining());
        log.error("参数校验失败，失败原因为：{}", message);
        return R.errorMsg(message);
    }

}
