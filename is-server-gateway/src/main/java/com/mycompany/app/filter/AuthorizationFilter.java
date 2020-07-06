package com.mycompany.app.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @Description:    //TODO  授权服务
 * @Author:         john
 * @CreateDate:     2020/6/26 15:40
 * @Version:        1.0
 */
@Component
@Slf4j
public class AuthorizationFilter extends ZuulFilter {

    private final String TOKEN_URI = "/token";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        log.info("authorization start");

        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        if(isNeedAuth(request)){

            TokenInfo tokenInfo = (TokenInfo) request.getAttribute("token");
            if(tokenInfo != null && tokenInfo.isActive()){

                if(!hasPermission(tokenInfo,request)){
                    log.info("audit log update fail 403");
                    handleError(403,requestContext);
                }

                // 添加 用户相关信息到 RequestHeader 对象中
                requestContext.addZuulRequestHeader("username",tokenInfo.getUser_name());

            }else {
                if (!StringUtils.startsWith(request.getRequestURI(),TOKEN_URI)){
                    log.info("audit log update fail 401");
                    handleError(401,requestContext);
                }

            }
        }
        return null;
    }


    /**
     * @description:   //TODO 当前请求是否授权
     * @author:        john
     * @param tokenInfo, request
     * @return:
     * @exception:
     * @date:          2020/6/26 15:54
     */
    private boolean hasPermission(TokenInfo tokenInfo, HttpServletRequest request) {
        //RandomUtils.nextInt() % 2 == 0
        return true;
    }


    /**
     * @description:   //TODO  处理授权错误
     * @author:        john
     * @param errorCode, requestContext
     * @return:
     * @exception:
     * @date:          2020/6/26 15:52
     */
    private void handleError(int errorCode, RequestContext requestContext) {

        requestContext.getResponse().setContentType("application/json");
        requestContext.setResponseStatusCode(errorCode);
    requestContext.setResponseBody("\"message\":\"auth fail\"");
        //停止流程向下处理
        requestContext.setSendZuulResponse(false);
    }

    private boolean isNeedAuth(HttpServletRequest request) {

        return true;
    }
}
