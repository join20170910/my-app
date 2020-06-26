package com.mycompany.app.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * @Description:    //TODO 日志审核功能
 * @Author:         john
 * @CreateDate:     2020/6/26 15:33
 * @Version:        1.0
 */

@Component
@Slf4j
public class AuditLogFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        log.info("audit log insert");

        return null;
    }
}
