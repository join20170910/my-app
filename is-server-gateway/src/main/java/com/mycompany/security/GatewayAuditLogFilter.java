package com.mycompany.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName GatewayAuditLogFilter
 * @Deacription TODO 日志 审核 过滤器 记录 日志
 * @Author apple
 * @Date 2020/7/20 22:04
 * @Version 1.0
 **/
@Slf4j
public class GatewayAuditLogFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        logger.info("1. add log for "+username);
        filterChain.doFilter(request,response);
        if(StringUtils.isBlank((String) request.getAttribute("logUpdated"))){
            logger.info("3. update log to success");
        }
    }
}
