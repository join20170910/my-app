package com.mycompany.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author john
 */
@Data
@ToString
public class TokenInfo {
  private String access_token;
  private String refresh_token;
  private String token_type;
  private Long expires_in;
  private String scope;

  //过期时间

  private LocalDateTime expireTime;

  /**
   * 计算 token的有效时长 减一个秒数， 确保token 过期前做刷新
   * @return
   */
  public TokenInfo init(){
    expireTime = LocalDateTime.now().plusSeconds(expires_in -10);
    return this;
  }

  /**
   * 判断 token 是否过期
   * @return
   */
  public boolean isExpired() {
    return expireTime.isBefore(LocalDateTime.now());
  }

}
