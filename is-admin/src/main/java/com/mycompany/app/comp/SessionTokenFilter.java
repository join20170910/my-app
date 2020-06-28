package com.mycompany.app.comp;

import com.mycompany.app.dto.TokenInfo;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author john
 */
@Component
public class SessionTokenFilter extends ZuulFilter {

  //oauth/check_token
  private final String TOKEN_CHECK_URL = "http://gateway.imooc.com:9070/token/oauth/token";
  private RestTemplate restTemplate = new RestTemplate();
  private HttpHeaders headers = new HttpHeaders();
  private final String TOKEN_START = "bearer ";
  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 0;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() throws ZuulException {

    RequestContext requestContext = RequestContext.getCurrentContext();
    HttpServletRequest request = requestContext.getRequest();
    TokenInfo tokenInfo = (TokenInfo) request.getSession().getAttribute("token");
    if(tokenInfo != null){
      String token = tokenInfo.getAccess_token();
      if(tokenInfo.isExpired()){
        //TODO 刷新令牌

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("admin","123456");
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

        params.add("grant_type","reflesh_token");
        params.add("reflesh_token",tokenInfo.getRefresh_token());


        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,headers);

        ResponseEntity<TokenInfo> response = restTemplate.exchange(TOKEN_CHECK_URL, HttpMethod.POST,entity,TokenInfo.class);
        request.getSession().setAttribute("token",response.getBody().init());
        token = response.getBody().getAccess_token();
      }
     // requestContext.addZuulRequestHeader("Authorization",TOKEN_START + tokenInfo.getAccess_token());
      requestContext.addZuulRequestHeader("Authorization","bearer "+ token);
    }

    return null;
  }
}
