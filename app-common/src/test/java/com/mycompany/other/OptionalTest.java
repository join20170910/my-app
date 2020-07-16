package com.mycompany.other;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class OptionalTest {

  /**
   * @description: //TODO 指定月的自然天数
   * @author: john
   * @return:
   * @exception:
   * @date: 2020/7/8 11:28
   */
  @Test
  public void curentMounthByDays() {

    int year = 2020;
    int month = 5;

    Calendar c = Calendar.getInstance();
    c.set(Calendar.YEAR, year);
    //  月份是从0开始计算，所bai以需要减1
    c.set(Calendar.MONTH, month - 1);
    //  当月最后一天的du日期
    int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
    //  开始日期为1号
    int start = 1;
    //  计数
    int count = 0;
    while (start <= max) {
      c.set(Calendar.DAY_OF_MONTH, start);
      if (isWorkDay(c)) {
        count++;
      }
      start++;
    }
    System.out.println(count);
  }


  @Test
  public void getNaturalDaysOfMonth() throws Exception {

      int year = 2020;
      int month = 6;
      Integer max = getCurrentNaturalDayOfMounth();
      Integer max1 = getNaturalDayOfMounth(year,month);

      System.out.println(max);
      System.out.println(max1);
  }


  /**
   * @description:   //TODO  获取当前月自然天
   * @author:        john
   * @return:        int
   * @exception:     Exception
   * @date:          2020/7/8 11:54
   */
  public int getCurrentNaturalDayOfMounth() throws Exception {

    Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH) + 1;
    int year = cal.get(Calendar.YEAR);
    return getNaturalDayOfMounth(year,month);

  }

  /**
   * @description:   //TODO
   * @author:        john
   * @param year, month
   * @return:     int
   * @exception:     Exception
   * @date:          2020/7/8 11:54
   */
  public int getNaturalDayOfMounth(Integer year,Integer month) throws Exception {

    Calendar c = Calendar.getInstance();

    if(year == null || month == null){
       throw new Exception("参数错误");
    }
    c.set(Calendar.YEAR, year);
    //  月份是从0开始计算，所bai以需要减1
    c.set(Calendar.MONTH, month - 1);
    //  当月最后一天的du日期
    int max = c.getActualMaximum(Calendar.DAY_OF_MONTH);
    return max;
  }

  @Test
  public void main() {
    Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DATE);
    int month = cal.get(Calendar.MONTH) + 1;
    int year = cal.get(Calendar.YEAR);
    int dow = cal.get(Calendar.DAY_OF_WEEK);
    int dom = cal.get(Calendar.DAY_OF_MONTH);
    int doy = cal.get(Calendar.DAY_OF_YEAR);

    System.out.println("Current Date: " + cal.getTime());
    System.out.println("Day: " + day);
    System.out.println("Month: " + month);
    System.out.println("Year: " + year);
    System.out.println("Day of Week: " + dow);
    System.out.println("Day of Month: " + dom);
    System.out.println("Day of Year: " + doy);
  }
  //  判断是否工作日（未排除法定节假日，由于涉及到农历节日，处理很麻烦）
  public static boolean isWorkDay(Calendar c) {
    //  获取星期,1~7,其中1代表星期日，2代表星期一  ...  7代表星期六
    int week = c.get(Calendar.DAY_OF_WEEK);
    //  不是周六和周日的都认为是工作日
    return week != Calendar.SUNDAY && week != Calendar.SATURDAY;
  }


  @Test
  public void testParse() {
    ExecutorService executorService = Executors.newCachedThreadPool();
    List<String> dateStrList = Lists.newArrayList(
            "2018-04-01 10:00:01",
            "2018-04-02 11:00:02",
            "2018-04-03 12:00:03",
            "2018-04-04 13:00:04",
            "2018-04-02 11:00:02",
            "2018-04-03 12:00:03",
            "2018-04-04 13:00:04",
            "2018-04-02 11:00:02",
            "2018-04-03 12:00:03",
            "2018-04-04 13:00:04",
            "2018-04-02 11:00:02",
            "2018-04-03 12:00:03",
            "2018-04-04 13:00:04",
            "2018-04-05 14:00:05"
    );
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    for (String str : dateStrList) {
      executorService.execute(
          () -> {
            try {
              simpleDateFormat.parse(str);
              System.out.println(simpleDateFormat.parse(str));
              TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
              e.printStackTrace();
            }
          });
    }
  }
}
