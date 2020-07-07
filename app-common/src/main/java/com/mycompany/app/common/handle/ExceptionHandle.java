package com.mycompany.app.common.handle;

import com.mycompany.app.common.domain.Result;
import com.mycompany.app.common.exception.BusiException;
import com.mycompany.app.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName ExceptionHandle
 * @Deacription TODO 統一異常捕獲
 * @Author apple
 * @Date 2020/7/7 22:40
 * @Version 1.0
 **/

@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Result handle(Exception e){

        if (e instanceof BusiException){
            BusiException busiException = (BusiException) e;
            return ResultUtil.error(busiException.getCode(),e.getMessage());
        }
        log.error("[系统异常] {}",e.getMessage());
        return ResultUtil.error(-1,"未知错误");
    }
}
