package com.mycompany.app.dto;

import lombok.Data;

/**
 * @author john
 */
@Data
public class TokenInfo {
  private String access_token;
  private String token_type;
  private String expires_in;
  private String scope;
}
