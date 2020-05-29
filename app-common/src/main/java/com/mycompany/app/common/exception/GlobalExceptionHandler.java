package com.mycompany.app.common.exception;

import com.mycompany.app.common.domain.JsonData;
import com.mycompany.app.common.utils.api.ResultCode;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 异常处理控制器 SpringMVC自定义异常对应的status code
 *
 * <p>Exception HTTP Status Code ConversionNotSupportedException 500 (Internal Server Error)
 * HttpMessageNotWritableException 500 (Internal Server Error) HttpMediaTypeNotSupportedException
 * 415 (Unsupported Media Type) HttpMediaTypeNotAcceptableException 406 (Not Acceptable)
 * HttpRequestMethodNotSupportedException 405 (Method Not Allowed)
 * NoSuchRequestHandlingMethodException 404 (Not Found) TypeMismatchException 400 (Bad Request)
 * HttpMessageNotReadableException 400 (Bad Request) MissingServletRequestParameterException 400
 * (Bad Request)
 *
 * @author john
 */

@RestControllerAdvice(annotations={RestController.class, Controller.class})
public class GlobalExceptionHandler  {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @ExceptionHandler({MissingServletRequestParameterException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonData handleError(HttpServletRequest request,MissingServletRequestParameterException e) {
    logger.warn("缺少请求参数 请求方法为:{}, 请求url为: {}", request.getMethod(), request.getRequestURI(),e.getMessage());
    String message = String.format("缺少必要的请求参数: %s", e.getParameterName());

    return JsonData.buildError( message,null,ResultCode.PARAM_TYPE_ERROR.getCode(),request.getMethod()+ request.getRequestURI());
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonData handleError(MethodArgumentTypeMismatchException e) {
    logger.warn("请求参数格式错误 请求方法为:{}, 请求url为: {}", e.getMessage());
    String message = String.format("请求参数格式错误: %s", e.getName());
    return JsonData.buildError(message,ResultCode.PARAM_TYPE_ERROR.getCode());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonData handleError(MethodArgumentNotValidException e) {
    logger.warn("参数验证失败 请求方法为:{}, 请求url为: {}", e.getMessage());
    return handleError(e.getBindingResult());
  }

  @ExceptionHandler(BindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonData handleError(BindException e) {
    logger.warn("参数绑定失败 请求方法为:{}, 请求url为: {}", e.getMessage());
    return handleError(e.getBindingResult());
  }

  private JsonData handleError(BindingResult result) {
    FieldError error = result.getFieldError();
    String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
    return JsonData.buildSuccess(ResultCode.PARAM_BIND_ERROR, message);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonData handleError(ConstraintViolationException e) {
    logger.warn("参数验证失败 请求方法为:{}, 请求url为: {}", e.getMessage());
    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    ConstraintViolation<?> violation = violations.iterator().next();
    String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
    String message = String.format("%s:%s", path, violation.getMessage());
    return JsonData.buildSuccess(ResultCode.PARAM_VALID_ERROR, message);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public JsonData handleError(NoHandlerFoundException e) {
    logger.error("404没找到请求:{} 请求方法为:{}, 请求url为: {}", e.getMessage());
    return JsonData.buildSuccess(ResultCode.NOT_FOUND, e.getMessage());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public JsonData handleError(HttpMessageNotReadableException e) {
    logger.error("消息不能读取:{} 请求方法为:{}, 请求url为: {}", e.getMessage());
    return JsonData.buildSuccess(ResultCode.MSG_NOT_READABLE, e.getMessage());
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
  public JsonData handleError(HttpRequestMethodNotSupportedException e) {
    logger.error("不支持当前请求方法:{} 请求方法为:{}, 请求url为: {}", e.getMessage());
    return JsonData.buildSuccess(ResultCode.METHOD_NOT_SUPPORTED, e.getMessage());
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  public JsonData handleError(HttpMediaTypeNotSupportedException e) {
    logger.error("不支持当前媒体类型:{} 请求方法为:{}, 请求url为: {}", e.getMessage());
    return JsonData.buildSuccess(ResultCode.MEDIA_TYPE_NOT_SUPPORTED, e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public JsonData handlerException(Exception e) {
    logger.error("服务器异常 请求方法为:{}, 请求url为: {}", e);
    // 发送服务异常事件
    return JsonData.buildSuccess(
            ResultCode.INTERNAL_SERVER_ERROR,
            (ObjectUtils.isEmpty(e.getMessage())
                    ? ResultCode.INTERNAL_SERVER_ERROR.getMessage()
                    : e.getMessage()));
  }

  @ExceptionHandler(Throwable.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public JsonData handleError(Throwable e) {
    logger.error("服务器异常", e);
    // 发送服务异常事件
    return JsonData.buildSuccess(
        ResultCode.INTERNAL_SERVER_ERROR,
        (ObjectUtils.isEmpty(e.getMessage())
            ? ResultCode.INTERNAL_SERVER_ERROR.getMessage()
            : e.getMessage()));
  }

}
