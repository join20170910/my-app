package com.mycompany.common.enums;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author apple
 * @Description //TODO
 * @Date 22:58 2020/7/7
 * @Param
 * @return
 **/
public enum ResultEnum {


    /**
     * 请求未授权 401
     */
    UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "请求未授权"),

    /**
     * 404 没找到请求
     */
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404 没找到请求"),

    /** 未知错误 */
    UNKONW_ERROR(-1,"未知错误"),

    /**  操作成功 */
    SUCCESS(200,"成功");


    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }


    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
