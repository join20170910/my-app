package com.mycompany.app.common.domain;

/**
 * @ClassName Result
 * @Deacription TODO http 请求返回的最外层对象
 * @Author apple
 * @Date 2020/7/7 22:18
 * @Version 1.0
 **/
public class Result<T> {
    /**
     * 错误 码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体的内容
     */
    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
