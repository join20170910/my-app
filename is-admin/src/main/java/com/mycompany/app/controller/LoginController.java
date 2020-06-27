package com.mycompany.app.controller;

import com.mycompany.app.dto.Credentials;
import com.mycompany.app.dto.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author john
 */
@RestController
@Slf4j
public class LoginController {

  //oauth/check_token
  private final String TOKEN_CHECK_URL = "http://gateway.imooc.com:9070/token/oauth/token";
  private RestTemplate restTemplate = new RestTemplate();

  @PostMapping("/login")
  public void login(HttpServletRequest request, @RequestBody Credentials credentials){
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.setBasicAuth("admin","123456");
    MultiValueMap<String,String> params = new LinkedMultiValueMap<>();

    params.add("username",credentials.getUsername());
    params.add("password",credentials.getPassword());
    params.add("grant_type","password");
    params.add("scope","read write");


    HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,headers);

    ResponseEntity<TokenInfo> response = restTemplate.exchange(TOKEN_CHECK_URL, HttpMethod.POST,entity,TokenInfo.class);
    request.getSession().setAttribute("token",response.getBody());
    log.info("token info is {}",response.getBody().toString());
  }

  @PostMapping("/logout")
  public void logout(HttpServletRequest request, @RequestHeader String username){
    log.info("当前用户注销成功：{}",username );
    request.getSession().invalidate();
  }
}
