package com.mycompany.app.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/** @author john */
@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

  private RestTemplate restTemplate = new RestTemplate();


  /**
   * @description:   //TODO
   * @author:        john
   * @param info   userName 当前调用的用户信息
   * @return:
   * @exception:
   * @date:          2020/6/24 22:45
   */
  @PostMapping
  public OrderInfo create(@RequestBody OrderInfo info, @AuthenticationPrincipal String userName) {
    //PriceInfo priceInfo = restTemplate.getForObject("http://localhost:9060/prices/" +info.getProductId(), PriceInfo.class);
    //log.info("price is {},测试：{}",priceInfo.getPrice(),"haha");
    return info;
  }
}
