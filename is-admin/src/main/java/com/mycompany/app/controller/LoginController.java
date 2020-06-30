package com.mycompany.app.controller;

import com.mycompany.app.dto.Credentials;
import com.mycompany.app.dto.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author john
 * @Description //TODO  登陆相关的功能
 * @Date 16:12 2020/6/28
 **/
@RestController
@Slf4j
public class LoginController {

  //oauth/check_token
  private final String TOKEN_CHECK_URL = "http://gateway.imooc.com:9070/token/oauth/token";
  private RestTemplate restTemplate = new RestTemplate();
  private HttpHeaders headers = new HttpHeaders();

  @PostMapping("/login")
  public void login(HttpServletRequest request, @RequestBody Credentials credentials){

    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setBasicAuth("admin","123456");
    MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

    params.add("username",credentials.getUsername());
    params.add("password",credentials.getPassword());
    params.add("grant_type","password");
    params.add("scope","read write");


    HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,headers);

    ResponseEntity<TokenInfo> response = restTemplate.exchange(TOKEN_CHECK_URL, HttpMethod.POST,entity,TokenInfo.class);
    request.getSession().setAttribute("token",response.getBody().init());
    log.info("token info is {}",response.getBody().toString());
  }

  /**
   * @Author john
   * @Description //TODO 注销当前用户
   * @Date 19:16 2020/6/28
   * @Param [request]
   * @return void
   **/
  @PostMapping("/logout")
  public void logout(HttpServletRequest request){
    log.info(" session 注销成功 ");
    request.getSession().invalidate();
  }

  /**
   * @Author john
   * @Description //TODO oauth2 回调方法
   * @Date 16:10 2020/6/28
   * @Param [code, state, request]
   * @return void
   **/
  @GetMapping("/oauth/callback")
  public void callback(@RequestParam String code, String state, HttpServletRequest request, HttpServletResponse response) throws IOException {

    log.info("state is {}" ,state);
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setBasicAuth("admin","123456");
    MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

    params.add("code",code);
    params.add("grant_type","authorization_code");
    params.add("redirect_uri","http://admin.imooc.com:8080/oauth/callback");

    HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,headers);
    ResponseEntity<TokenInfo> responseEntity = restTemplate.exchange(TOKEN_CHECK_URL, HttpMethod.POST,entity,TokenInfo.class);
    request.getSession().setAttribute("token",responseEntity.getBody());
    response.sendRedirect("/");

  }

  @GetMapping("/me")
  public TokenInfo me(HttpServletRequest request){
    TokenInfo tokenInfo = (TokenInfo) request.getSession().getAttribute("token");
    return tokenInfo;
  }
}
