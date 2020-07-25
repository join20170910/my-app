package com.mycompany.common.utils;

import com.mycompany.common.domain.Result;

/**
 * @ClassName ResultUtil
 * @Deacription TODO  统一返回工具类
 * @Author apple
 * @Date 2020/7/7 22:26
 * @Version 1.0
 **/
public class ResultUtil {

    public static Result success(Object object) {
        Result result = new Result();

        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
