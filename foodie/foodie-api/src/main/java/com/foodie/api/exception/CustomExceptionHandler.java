package com.foodie.api.exception;

import com.foodie.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 自定义异常捕获类
 * @author jamie
 * @date 2020/8/13 13:12
 */
@RestControllerAdvice
@Order(1)
@Slf4j
public class CustomExceptionHandler {

    // 上传文件超过500k，捕获异常：MaxUploadSizeExceededException

    /**
     * 上传文件超过500k，捕获异常：MaxUploadSizeExceededException
     * @param ex 图片超大异常
     * @return com.foodie.common.utils.R<java.lang.String>
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public R<String> handlerMaxUploadFile(MaxUploadSizeExceededException ex) {
        log.warn("上传图片超过配置大小");
        return R.errorMsg("文件上传大小不能超过500k，请压缩图片或者降低图片质量再上传！");
    }

}
