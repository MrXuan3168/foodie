package com.foodie.common.utils;

/**
 * 应用模块名称：统一返回前端对象
 *
 * @since 2019/11/25 11:42
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 自定义响应数据结构 </br>
 * 本类可提供给 H5/ios/安卓/公众号/小程序 使用 </br>
 * 前端接受此类数据（json object)后，可自行根据业务去实现相关功能 </br>
 * 200：表示成功 </br>
 * 500：表示错误，错误信息在msg字段中 </br>
 * 501：bean验证错误，不管多少个错误都以map形式返回 </br>
 * 502：拦截器拦截到用户token出错 </br>
 * 555：异常抛出信息 </br>
 * 556: 用户qq校验异常
 *
 * @author jamie
 */
@Getter
@ToString
@NoArgsConstructor
@ApiModel(value = "客户端返回对象", description = "从客户端返回的对象，为json格式")
public class R<T> {

    /** 响应业务状态 */
    @ApiModelProperty(value = "接口状态码", example = "200", required = true)
    private Integer status;

    /** 响应消息 */
    @ApiModelProperty(value = "响应消息", example = "OK")
    private String msg;

    /** 响应数据 */
    @ApiModelProperty(value = "响应数据")
    private T data;

    /** 构造方法 */
    private R(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private R(Integer status, String msg) {
        this.status = status;
        this.msg = msg;

    }

    private R(T data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public static <T> R<T> build(Integer status, String msg, T data) {
        return new R<>(status, msg, data);
    }

    public static <T> R<T> ok(T data) {
        return new <T>R<T>(data);
    }

    public static <T> R<T> ok() {
        return new <T>R<T>(null);
    }

    public static <T> R<T> errorMsg(String msg) {
        return new <T>R<T>(500, msg);
    }

    public static <T> R<T> errorMap(T data) {
        return new <T>R<T>(501, "error", data);
    }

    public static <T> R<T> errorTokenMsg(String msg) {
        return new <T>R<T>(502, msg);
    }

    public static <T> R<T> errorException(String msg) {
        return new <T>R<T>(555, msg);
    }

    public static <T> R<T> errorUserQQ(String msg) {
        return new <T>R<T>(556, msg);
    }

}
