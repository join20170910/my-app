package com.mycompany.app.bean;

import lombok.Data;

/** @author john */
@Data
public class User {
  // ����

  private String name;
  // �û�����

  private int level;

  public User() {}

  public User(String name, int level) {
    this.name = name;
    this.level = level;
  }
}
