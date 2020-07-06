package com.mycompany.app.bean;

import lombok.Data;

/** @author john */
@Data
public class User {
  // 姓名

  private String name;
  // 用户级别

  private int level;

  public User() {}

  public User(String name, int level) {
    this.name = name;
    this.level = level;
  }
}
