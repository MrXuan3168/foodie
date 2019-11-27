package com.so.utils;

/**
 * 应用模块名称：统一返回前端对象
 * 
 * @author show
 * @since 2019/11/25 11:42
 */

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description: 自定义响应数据结构 </br>
 *               本类可提供给 H5/ios/安卓/公众号/小程序 使用 </br>
 *               前端接受此类数据（json object)后，可自行根据业务去实现相关功能 </br>
 *               200：表示成功 </br>
 *               500：表示错误，错误信息在msg字段中 </br>
 *               501：bean验证错误，不管多少个错误都以map形式返回 </br>
 *               502：拦截器拦截到用户token出错 </br>
 *               555：异常抛出信息 </br>
 *               556: 用户qq校验异常
 * @author show
 * @date 2019/11/25 11:45
 */
@Getter
@ToString
@NoArgsConstructor
@ApiModel(value = "客户端返回对象", description = "从客户端返回的对象，为json格式")
public class ServerResponseResult<T> implements Serializable {

    /** 响应业务状态 */
    @ApiModelProperty(value = "接口状态码", name = "status", example = "200", required = true)
    private Integer status;
    /** 响应消息 */
    @ApiModelProperty(value = "响应消息", name = "msg", example = "OK")
    private String msg;
    /** 响应数据 */
    @ApiModelProperty(value = "响应数据", name = "data", example = "Object")
    private T data;

    public static <T> ServerResponseResult<T> build(Integer status, String msg, T data) {
        return new ServerResponseResult<>(status, msg, data);
    }

    public static <T> ServerResponseResult<T> ok(T data) {
        return new <T>ServerResponseResult<T>(data);
    }

    public static <T> ServerResponseResult<T> ok() {
        return new <T>ServerResponseResult<T>(null);
    }

    public static <T> ServerResponseResult<T> errorMsg(String msg) {
        return new <T>ServerResponseResult<T>(500, msg, null);
    }

    public static <T> ServerResponseResult<T> errorMap(T data) {
        return new <T>ServerResponseResult<T>(501, "error", data);
    }

    public static <T> ServerResponseResult<T> errorTokenMsg(String msg) {
        return new <T>ServerResponseResult<T>(502, msg, null);
    }

    public static <T> ServerResponseResult<T> errorException(String msg) {
        return new <T>ServerResponseResult<T>(555, msg, null);
    }

    public static <T> ServerResponseResult<T> errorUserQQ(String msg) {
        return new <T>ServerResponseResult<T>(556, msg, null);
    }

    /** 构造方法 */
    private ServerResponseResult(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponseResult(T data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

}
