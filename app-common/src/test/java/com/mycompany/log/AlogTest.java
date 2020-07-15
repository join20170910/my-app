package com.mycompany.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlogTest {
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  private Logger dataLogger = LoggerFactory.getLogger("dataLogger");

  @Test
  public void logTest() {
    dataLogger.info("dataLogger--------");
  }


}
