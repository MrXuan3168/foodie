package com.so.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 应用模块名称：监控service执行时间<br>
 * AOP通知：<br>
 * 1.前置通知：在方法之前调用<br>
 * 2.后置通知：在方法正常调用之后通知<br>
 * 3.环绕通知：在方法调用之前和之后，都分别可以执行的通知<br>
 * 4.异常通知：如果在方法调用过程中发生异常，则通知<br>
 * 5.最终通知：在方法调用之后通知<br>
 * 
 * @author show
 * @since 2019/11/25 23:43
 */
@Component
@Aspect
@Slf4j
public class ServiceLogAspect {
    private static final long ERROR_TAKE_TIME = 3000;
    private static final long WARN_TAKE_TIME = 2000;

    /**
     * 切面表达式<br>
     * execution 代表所要执行的表达式主体<br>
     * 第一处 * 代表方法返回类型 *代表所有类型<br>
     * 第二处 包名代表aop监控的类所在的包<br>
     * 第三处 .. 代表该包以及其子包下的所有类方法<br>
     * 第四处 * 代表类名，*代表所有类<br>
     * 第五处 *(..) *代表类中的方法名，(..)表示方法中的任何参数<br>
     * 
     * @author show
     * @date 2019/11/25 23:53
     */
    @Around("execution(* com.so.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("===== 开始执行{}.{} =====", joinPoint.getTarget().getClass(), joinPoint.getSignature().getName());
        // 记录开始时间
        long begin = System.currentTimeMillis();

        // 执行目标 service
        Object result = joinPoint.proceed();
        // 记录开始时间
        long end = System.currentTimeMillis();
        long takeTime = end - begin;
        if (takeTime > ERROR_TAKE_TIME) {
            log.error("===== 执行结束，耗时：{}毫秒 =====", takeTime);
        } else if (takeTime > WARN_TAKE_TIME) {
            log.warn("===== 执行结束，耗时：{}毫秒 =====", takeTime);
        } else {
            log.info("===== 执行结束，耗时：{}毫秒 =====", takeTime);
        }
        return result;
    }

}
