package com.mycompany.app.bean;

import lombok.Data;

@Data
public class NumCount {

  private int count;

  public void plus() {
    count = count + 1;
  }
}
