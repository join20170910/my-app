package com.mycompany.app.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: //TODO 统一权限认证功能
 * pre：可以在请求被路由之前调用
 * routing：在路由请求时候被调用
 * post：在routing和error过滤器之后被调用。
 * error：处理请求时发生错误时被调用。
 * @Author: john
 * @CreateDate: 2020/6/26 12:13
 * @Version: 1.0
 */
@Component
@Slf4j

public class OauthFilter extends ZuulFilter {

    private final String TOKEN_URI = "/token";
    private final String TOKEN_START = "bearer ";
    private RestTemplate restTemplate = new RestTemplate();
    //oauth/check_token
    private final String TOKEN_CHECK_URL = "http://localhost:9090/oauth/check_token";


    // filter 类型：
  // pre：可以在请求被路由之前调用。
  // routing：在路由请求时候被调用。
  // post：在routing和error过滤器之后被调用。
  // error：处理请求时发生错误时被调用。

  @Override
  public String filterType() {
        return "pre";
    }

    //执行顺序

    @Override
    public int filterOrder() {
        return 1;
    }

    //是否启动

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

      log.info("oauth start ");
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 获取 request 对象
        HttpServletRequest request = requestContext.getRequest();
        //如果 为 token 开头的 uri 直接转发到 权限 认证服务器
        if(StringUtils.startsWith(request.getRequestURI(),TOKEN_URI)){
            return null;
        }
        String authHeader = request.getHeader("Authorization");

        if(StringUtils.isBlank(authHeader)){
            return null;
        }
        if(!StringUtils.startsWithIgnoreCase(authHeader,TOKEN_START)){
            return null;
        }

        try{

            TokenInfo tokenInfo = getTokenInfo(authHeader);
            request.setAttribute("token",tokenInfo);
        }catch (Exception e){
            log.error("get token info fail",e);
        }
        return null;
    }


    /**
     * @description:   //TODO token 解析
     * @author:        john
     * @param authHeader
     * @return:
     * @exception:
     * @date:          2020/6/26 13:10
     */
    private TokenInfo getTokenInfo(String authHeader) {

      String token = StringUtils.substringAfter(authHeader,TOKEN_START);
      //构建请求
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("gateway","123456");
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("token",token);
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,headers);

        ResponseEntity<TokenInfo> response = restTemplate.exchange(TOKEN_CHECK_URL, HttpMethod.POST,entity,TokenInfo.class);
        log.info("token info is {}",response.getBody().toString());

        return response.getBody();
    }
}
