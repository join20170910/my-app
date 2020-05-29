package com.mycompany.app.common.exception;

import com.mycompany.app.common.domain.JsonData;
import com.mycompany.app.common.utils.api.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 *      1.注解@ControllerAdvice方式只能处理控制器抛出的异常。此时请求已经进入控制器中。
 *     <p>2.类ErrorController方式可以处理所有的异常，包括未进入控制器的错误，比如404,401等错误
 *     <p>3.如果应用中两者共同存在，则@ControllerAdvice方式处理控制器抛出的异常，类ErrorController方式未进入控制器的异常。
 *     <p>4.@ControllerAdvice方式可以定义多个拦截方法，拦截不同的异常类，并且可以获取抛出的异常信息，自由度更大。
 */
public class HttpErrorController implements ErrorController {
    private final static String ERROR_PATH = "/error";
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @ResponseBody
    @RequestMapping(path  = ERROR_PATH )
    public JsonData error(HttpServletRequest request, HttpServletResponse response){
        logger.info("访问/error" + "  错误代码："  + response.getStatus());
        JsonData result = JsonData.buildError("HttpErrorController error:"+response.getStatus(), ResultCode.INTERNAL_SERVER_ERROR.getCode());
        return result;
    }
    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
