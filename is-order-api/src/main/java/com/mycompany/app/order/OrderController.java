package com.mycompany.app.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/** @author john */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

  @Autowired
  private OAuth2RestTemplate restTemplate;


  /**
   * @description:   //TODO 创建 订单 显示 当前调用者的信息   相关使用@AuthenticationPrincipal(expression= "#this.id")
   * @author:        john
   * @param info   userName 当前调用的用户信息
   * @return:
   * @exception:
   * @date:          2020/6/24 22:45
   */
  @PostMapping
  public OrderInfo create(@RequestBody OrderInfo info, @AuthenticationPrincipal String username) {

    PriceInfo priceInfo = restTemplate.getForObject("http://localhost:9060/prices/" +info.getProductId(), PriceInfo.class);
    log.info("price is {},测试：{}",priceInfo.getPrice(),"haha");
    log.info("当前调用者的姓名: {} ID：{}" ,username);
    return info;
  }
  @GetMapping("/{id}")
  public OrderInfo getInfo(@PathVariable Long id,@RequestHeader String username){

    log.info("orderId is {}",id);
    log.info("当前调用者的姓名: {}" ,username);
    OrderInfo info = new OrderInfo();
    info.setId(id);
    info.setProductId(id *5);
    return info;
  }
}
