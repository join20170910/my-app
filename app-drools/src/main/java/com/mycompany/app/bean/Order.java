package com.mycompany.app.bean;

import lombok.Data;

import java.util.Date;

/** @author john */
@Data
public class Order {
  // �µ�����

  private Date bookingDate;
  // ����ԭ�۽��

  private int amout;
  // �µ���

  private User user;
  // ����

  private int score;

  public Order(Date bookingDate, int amout, User user, int score) {
    this.bookingDate = bookingDate;
    this.amout = amout;
    this.user = user;
    this.score = score;
  }

  public Order() {}
}
