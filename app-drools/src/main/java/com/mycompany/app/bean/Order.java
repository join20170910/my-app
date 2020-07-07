package com.mycompany.app.bean;

import lombok.Data;

import java.util.Date;

/** @author john */
@Data
public class Order {
  // 下单日期

  private Date bookingDate;
  // 订单原价金额

  private int amout;
  // 下单人

  private User user;
  // 积分

  private int score;

  public Order(Date bookingDate, int amout, User user, int score) {
    this.bookingDate = bookingDate;
    this.amout = amout;
    this.user = user;
    this.score = score;
  }

  public Order() {}
}
