package com.foodie.api.aspect;

import com.alibaba.fastjson.JSON;
import com.foodie.common.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 应用模块名称：controller访问日志类
 * @author show
 * @since 2020/1/4 16:57
 */

@Component
@Aspect
@Slf4j
public class RequestLogAspect {

    /**
     * Define a pointcut
     */
    @Pointcut("execution(public * com.foodie.api.controller.*.*(..))")
    public void controllerLog() {

    }

    /**
     * Print Log before controller
     */
    @Before("controllerLog()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request =
                ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        log.info("请求IP：{}", request.getRemoteAddr());
        log.info("请求路径：{}", request.getRequestURL());
        log.info("请求方式：{}", request.getMethod());
        log.info("请求参数：{}", getRequest(joinPoint));
    }

    private String getRequest(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        StringBuilder paramBuilder = new StringBuilder();
        for(Object obj: args){
            try{
                String param = JSON.toJSONString(obj);
                paramBuilder.append(param);
            }catch(Exception e){
                log.error("args is HttpServletRequest", e);
            }
        }
        return String.valueOf(paramBuilder);
    }

    /**
     * Print the time that request method execution spend
     */
    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        Object retVal = joinPoint.proceed(args);
        long endTime = System.currentTimeMillis();
        log.info("执行时间：{} ms", endTime - startTime);
        log.info("返回值：{}", JacksonUtils.objectToJson(retVal));
        return retVal;
    }

    /**
     * Print exception
     */
    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void afterThrowing(Throwable ex) {
        log.error("发生异常：{}", ex.toString());
    }

}
