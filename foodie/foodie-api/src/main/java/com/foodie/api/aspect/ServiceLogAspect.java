package com.foodie.api.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 应用模块名称：监控service执行时间
 * AOP通知：
 * 1.前置通知：在方法之前调用
 * 2.后置通知：在方法正常调用之后通知
 * 3.环绕通知：在方法调用之前和之后，都分别可以执行的通知
 * 4.异常通知：如果在方法调用过程中发生异常，则通知
 * 5.最终通知：在方法调用之后通知
 * @author jamie
 * @since 2019/11/25 23:43
 */
@Component
@Aspect
@Slf4j
public class ServiceLogAspect {

    /** 异常时间 执行方法超过该时间发出错误日志 */
    private static final long ERROR_TAKE_TIME = 3000;

    /** 警告时间 执行方法超过该时间发出警告日志 */
    private static final long WARN_TAKE_TIME = 2000;

    /**
     * 切面表达式
     * execution 代表所要执行的表达式主体
     * 第一处 * 代表方法返回类型 *代表所有类型
     * 第二处 包名代表aop监控的类所在的包
     * 第三处 .. 代表该包以及其子包下的所有类方法
     * 第四处 * 代表类名，*代表所有类
     * 第五处 *(..) *代表类中的方法名，(..)表示方法中的任何参数
     */
    @Around("execution(* com.foodie.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 执行的类名
        Class<?> aClass = joinPoint.getTarget().getClass();
        // 执行的方法名
        String name = joinPoint.getSignature().getName();
        log.info("===== 开始执行{}.{} =====", aClass, name);
        // 记录开始时间
        long begin = System.currentTimeMillis();
        // 执行目标 service
        Object result = joinPoint.proceed();
        // 记录结束时间
        long end = System.currentTimeMillis();
        // 计算花费时间
        long takeTime = end - begin;
        String out = "===== 执行结束" + aClass + "." + name + " 耗时：" + takeTime + "毫秒 =====";
        if(takeTime > ERROR_TAKE_TIME){
            log.error(out);
        }else if(takeTime > WARN_TAKE_TIME){
            log.warn(out);
        }else{
            log.info(out);
        }
        return result;
    }

}
