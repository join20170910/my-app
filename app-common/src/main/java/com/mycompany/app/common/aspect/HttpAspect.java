package com.mycompany.app.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName HttpAspect
 * @Deacription TODO 记录 请求日志  IP  方法  url class的 方法名
 * @Author apple
 * @Date 2020/7/7 23:35
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class HttpAspect {

    @Pointcut("execution(public * com.apple.concurrent.*(..))")
    public void log(){

    }
    @Before("log()")
    public void log(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        //method
        //ip
        log.info("url={} method={},ip={},class_method=", request.getRequestURL(),request.getMethod(),request.getRemoteAddr(),
                joinPoint.getSignature().getDeclaringTypeName()+joinPoint.getSignature().getName()+joinPoint.getArgs());
    }
    @After("log()")
    public void doAfter(){

    }
}
