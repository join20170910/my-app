package com.mycompany.common.annotation;
import java.lang.annotation.*;

/**
 * @Description:    //TODO 定义系统日志注解
 * @Author:         john
 * @CreateDate:     2020/7/8 9:19
 * @Version:        1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
