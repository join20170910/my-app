package com.mycompany;

import com.mycompany.common.utils.DateUtils;
import org.junit.Test;

public class AgogTest {

  @Test
  public void logTest() {
    System.out.println("test----test ");
  }
  @Test
  public void dataUtilsTest(){
    DateUtils.parseDate("2020-07-10");
    System.out.println(DateUtils.parseDate("2020-07-10"));
  }
}
