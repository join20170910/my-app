package com.mycompany.controller;

import com.mycompany.utils.BitMatrixUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/** @author john */
@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private Logger dataLogger = LoggerFactory.getLogger("dataLogger");

  @GetMapping("/test")
  public void demo(@RequestParam(value = "userName",required = true) String userName) {

    dataLogger.info("测试：" + userName);

    System.out.println(userName);
  }


  /**
   * @description:   //TODO 生成二维码配置
   * @author:        john
   * @return:
   * @exception:
   * @date:          2020/5/29 11:09
   */
  @GetMapping("add")
  public void saveOrder(@RequestParam(value = "video_url",required = true)String codeUrl,
                        HttpServletResponse response) throws Exception {

    if(codeUrl == null) {
      throw new NullPointerException();
    }
    BitMatrixUtils.getBitMatrix(response,codeUrl);


  }
}
