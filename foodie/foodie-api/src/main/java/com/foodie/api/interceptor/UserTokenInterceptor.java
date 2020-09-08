package com.foodie.api.interceptor;

import com.foodie.api.controller.BaseController;
import com.foodie.common.utils.JacksonUtils;
import com.foodie.common.utils.R;
import com.foodie.common.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 用户拦截器
 * @author jamie
 * @date 2020/9/8 12:41
 */
public class UserTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 拦截请求，在访问controller之前
     * @param request  请求上下文
     * @param response 返回上下文
     * @param handler  handler
     * @return boolean
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String userId = request.getHeader("headerUserId");
        String userToken = request.getHeader("headerUserToken");
        if(StringUtils.isBlank(userId) && StringUtils.isBlank(userToken)){
            returnErrorResponse(response, R.errorMsg("请登录..."));
            return false;
        }
        String uniqueToke = redisUtils.get(BaseController.REDIS_USER_TOKEN + ":" + userId);
        if(StringUtils.isBlank(uniqueToke)){
            returnErrorResponse(response, R.errorMsg("请登录..."));
            return false;
        }
        if(StringUtils.equals(uniqueToke, userToken)){
            returnErrorResponse(response, R.errorMsg("账号在异地登录..."));
            return false;
        }

        // false：请求被拦截，被驳回，验证出现问题
        // true：请求在经过验证校验以后，是OK的，是可以放行的
        return true;
    }

    /**
     * 封装返回
     * @param response 返回上下文
     * @param result   返回前端对象
     */
    public void returnErrorResponse(HttpServletResponse response, R<Objects> result) {
        try(OutputStream out = response.getOutputStream()){
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out.write(Objects.requireNonNull(JacksonUtils.objectToJson(result)).getBytes(StandardCharsets.UTF_8));
            out.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 请求访问controller之后，渲染视图之前
     * @param request      请求上下文
     * @param response     返回上下文
     * @param handler      handler
     * @param modelAndView 视图对象
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

    }

    /**
     * 请求访问controller之后，渲染视图之后
     * @param request  请求上下文
     * @param response 返回上下文
     * @param handler  handler
     * @param ex       异常对象
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) {

    }

}
