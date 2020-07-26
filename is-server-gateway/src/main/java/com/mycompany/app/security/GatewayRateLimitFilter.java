package com.mycompany.app.security;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName GatewayRateLimitFilter
 * @Deacription TODO 限流
 * @Author apple
 * @Date 2020/7/21 20:33
 * @Version 1.0
 **/
@Slf4j
public class GatewayRateLimitFilter extends OncePerRequestFilter {

    private RateLimiter rateLimiter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info(" 0 rate limit ");

        if (rateLimiter.tryAcquire()) {
            filterChain.doFilter(request, response);
        } else {
                 response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                 response.setContentType("application/json");
                 response.getWriter().write("{\"error\":\"too many request\"}");
                 response.getWriter().flush();
                 return;
        }
    }
}
