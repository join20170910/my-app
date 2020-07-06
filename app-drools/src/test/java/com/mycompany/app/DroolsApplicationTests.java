package com.mycompany.app;

import com.mycompany.app.bean.*;
import com.mycompany.app.bean.cal.Calculation;
import com.mycompany.app.bean.cal.Item;
import com.mycompany.app.bean.discount.BookOrder;
import com.mycompany.app.globa.GloableService;
import com.mycompany.app.globa.UserService;
import com.mycompany.app.service.RuleService;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DroolsApplicationTests {

  @Autowired private KieSession session;

  @Autowired private KieBase kieBase;

  @Autowired private GloableService service;

  @Autowired
  private RuleService ruleService;

  @Test
  public void people() {

    People people = new People();
    people.setName("张三");
    people.setSex(0);
    people.setDrlType("people");
    session.insert(people); // 插入
    session.fireAllRules(); // 执行规则
  }

  @Test
  public void cat() {
    Cat cat = new Cat();
    cat.setName("金");
    cat.setSex(1);
    session.insert(cat); // 插入
    session.fireAllRules(); // 执行规则
  }

  @Test
  public void charSetTest() {
    System.out.println("哈哈");
  }

  @Test
  public void from() {
    People p1 = new People(1, "达", "from");
    People p2 = new People(0, "秋", "from");
    People p3 = new People(3, "金", "from");
    Animal animal = new Animal();
    animal.setPeoples(new ArrayList<>());
    animal.getPeoples().add(p1);
    animal.getPeoples().add(p2);
    animal.getPeoples().add(p3);
    session.insert(animal); // 插入
    session.fireAllRules(); // 执行规则
  }

  @Test
  public void global() {
    People people = new People();
    people.setDrlType("global");
    session.insert(people); // 插入
    // 配置全局变量
    List<People> list = new ArrayList<>();
    NumCount numCount = new NumCount();
    // GloableService service = new GloableService();
    session.setGlobal("list", list);
    session.setGlobal("numCount", numCount);
    session.setGlobal("service", service);
    session.fireAllRules(); // 执行规则
    // 取出全局变量值
    System.out.println(session.getGlobal("list").toString());
    System.out.println((session.getGlobal("numCount")).toString());
  }

  @Test
  public void query() {
    session.insert(new People(1, "春", "query"));
    session.insert(new People(2, "夏", "query"));
    session.insert(new People(3, "秋", "query"));
    session.insert(new People(4, "冬", "query"));
    session.insert(new People(5, "达", "query"));
    QueryResults results = session.getQueryResults("queryPeople", "达", 5);
    for (QueryResultsRow row : results) {
      People p = (People) row.get("$p");
      System.out.println(p);
    }
  }

  /**
   * @description: //TODO declare 自定义fact对象
   *     <p>通过 kieBase.getFactType(域名，实事名)的方式获取实事对象并实例    通过 factType.set(实例，属性名，属性值)的方式来赋值变量
   * @author: john
   * @return:
   * @exception:
   * @date: 2020/7/3 10:04
   */
  @Test
  public void declareTest() throws IllegalAccessException, InstantiationException {
    FactType factType = kieBase.getFactType("com.mycompany.app.decar", "Love");
    Object obj = factType.newInstance();
    factType.set(obj, "feel", "sad");
    factType.set(obj, "continued", "永远");
    session.insert(obj);
    session.fireAllRules();
  }

  /**
   * @description: //TODO 测试方法，函数执行 导入规则文件需要使用到的外部规则文件或者变量，这里的使用方法跟java相同，
   *     但是不同于java的是，这里的import导入的不仅仅可以是一个类， 也可以是这个类中的某一个可访问的静态方法
   * @author: john
   * @return:
   * @exception:
   * @date: 2020/7/3 10:19
   */
  @Test
  public void impot() {
    People people = new People();
    people.setDrlType("impot");
    session.insert(people); // 插入
    session.fireAllRules(); // 执行规则
  }

  @Test
  public void update() {

    People people = new People();
    people.setName("达");
    people.setSex(0);
    people.setAge(17);
    people.setDrlType("update");
    session.insert(people); // 插入
    session.fireAllRules(); // 执行规则
  }

  /**
   * @description: //TODO 网络商城要举办活动（奖励额外积分） https://www.cnblogs.com/wyx312/p/5035516.html
   *     <p>　　订单原价金额在
   *     <p>　　100以下, 不加分
   *     <p>　　100-500 加100分
   *     <p>　　500-1000 加500分
   *     <p>　　1000 以上 加1000分
   * @author: john
   * @return:
   * @exception:
   * @date: 2020/7/3 16:46
   */
  @Test
  public void orderTest() throws Exception {
    List<Order> orderList = getInitData();

    for (int i = 0; i < orderList.size(); i++) {
      Order o = orderList.get(i);
      session.insert(o);
      session.fireAllRules();
      // 执行完规则后, 执行相关的逻辑
      addScore(o);
    }

    session.dispose();
  }

  /**
   * @description: //TODO 图书 打折 1 规则一 所购图书总价在100元以下的没有优惠 2 规则二 所购图书总价在100到200元的优惠20元 3 规则三
   *     所购图书总价在200到300元的优惠50元 4 规则四 所购图书总价在300元以上的优惠100元
   * @author: john
   * @return:
   * @exception:
   * @date: 2020/7/4 9:32
   */
  @Test
  public void bookDiscountTest() {

    // 构造订单对象，设置原始价格，由规则引擎根据优惠规则计算优惠后的价格

    BookOrder order = new BookOrder();
    order.setOriginalPrice(210D);

    // 将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配

    session.insert(order);

    // 激活规则引擎，如果规则匹配成功则执行规则

    session.fireAllRules();

    System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
  }

  // 测试比较操作符
  @Test
  public void comparisonOperatorTest() {

    ComparisonOperatorEntity comparisonOperatorEntity = new ComparisonOperatorEntity();

    comparisonOperatorEntity.setNames("张三");
    List<String> list = new ArrayList<String>();
    list.add("张三");
    list.add("李四");

    comparisonOperatorEntity.setList(list);

    // 将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配，如果规则匹配成功则执行规则

    session.insert(comparisonOperatorEntity);
    // session.fireAllRules();
    // 通过规则过滤器实现只执行指定规则

    session.fireAllRules(new RuleNameEqualsAgendaFilter("rule_comparison_memberOf"));
  }

  @Test
  public void update2Test() {
    Student student = new Student();
    student.setAge(5);
    // 将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配，
    // 如果规则匹配成功则执行规则
    session.insert(student);
    session.fireAllRules();
  }

  @Test
  public void insertTest() {
    Student student = new Student();
    student.setAge(10);
    // 将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配，
    // 如果规则匹配成功则执行规则
    session.insert(student);

    session.fireAllRules();
  }

  @Test
  public void retractTest() {
    Student student = new Student();
    student.setAge(10);
    // 将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配，如果规则匹配成功则执行规则
    session.insert(student);
    session.fireAllRules();
  }

  // 模拟 死循环 rule_noloop属性用于防止死循环
  @Test
  public void ruleNoloopTest() {

    Student student = new Student();
    student.setAge(25);

    // 将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配，
    // 如果规则匹配成功则执行规则
    session.insert(student);
    session.fireAllRules();
  }

  @Test
  public void activationgroupTest() {

    session.fireAllRules();
  }

  @Test
  public void agendaGroupTest() {
    // 设置焦点，对应agenda-group分组中的规则才可能被触发
    session.getAgenda().getAgendaGroup("myagendagroup_1").setFocus();
    session.fireAllRules();
  }

  @Test
  public void timerTest() throws InterruptedException {

    new Thread(
            new Runnable() {
              public void run() {
                // 启动规则引擎进行规则匹配，直到调用halt方法才结束规则引擎
                session.fireUntilHalt();
              }
            })
        .start();

    Thread.sleep(10000);

    // 结束规则引擎
    session.halt();
  }

  @Test
  public void dateexpiresTest() {

    // 设置日期格式
    System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm");
    session.fireAllRules();
  }

  @Test
  public void testglobal() {

    // 设置全局变量，名称和类型必须和规则文件中定义的全局变量名称对应
    session.setGlobal("userService", new UserService());
    session.setGlobal("count", 5);
    List list = new ArrayList(10);
    list.add("dddd");
    list.add("dddd");
    list.add("dddd");
    session.setGlobal("gList", list);
    session.fireAllRules();
    // 因为在规则中为全局变量添加了两个元素，所以现在的size为2
    System.out.println(list.size());
  }

  @Test
  public void queryTest() {

    Student student1 = new Student();

    student1.setName("张三");

    student1.setAge(12);

    Student student2 = new Student();

    student2.setName("李四");

    student2.setAge(8);

    Student student3 = new Student();

    student3.setName("王五");

    student3.setAge(22);

    session.insert(student1);
    session.insert(student2);
    session.insert(student3);
    //调用规则文件中的查询

    QueryResults results1 = session.getQueryResults("query_1");

    int size = results1.size();

    System.out.println("size=" + size);

    for (QueryResultsRow row : results1) {

      Student student = (Student) row.get("$student");

      System.out.println(student);

    }


//调用规则文件中的查询
    QueryResults results2 = session.getQueryResults("query_2","王五");
    size = results2.size();
    System.out.println("size=" + size);
    for (QueryResultsRow row : results2) {
      Student student = (Student) row.get("$student");
      System.out.println(student);
    }
  }

  @Test
  public void calTest(){

    Calculation calculation = new Calculation();
    double wage = 0.15;
    calculation.setWage(wage);
    session.insert(calculation);
    //session.fireAllRules();
  }

  @Test
  public void ruleServiceTest(){

    Calculation calculation = new Calculation();
    double wage = 5000;
    calculation.setWage(wage);
    Calculation calculation1 = (Calculation) ruleService.calculate(calculation);

    System.out.println("---------------------------------------------");
    System.out.println(calculation.toString());
    System.out.println("---------------------------------------------");
  }

  @Test
public void itemTest(){
  Item item = new Item(1,1,2.0);
  Item item2 = new Item(2,1,3.0);
  session.insert(item);
  session.insert(item2);
  session.fireAllRules();

}




  @AfterEach
  public void runDispose() {
    session.dispose(); // 释放资源
  }

  private static void addScore(Order o) {
    System.out.println("用户" + o.getUser().getName() + "享受额外增加积分: " + o.getScore());
  }

  List<Order> getInitData() throws Exception {
    List<Order> orderList = new ArrayList<Order>();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    {
      Order order = new Order();
      order.setAmout(80);
      order.setBookingDate(df.parse("2015-07-01"));
      User user = new User();
      user.setLevel(1);
      user.setName("Name1");
      order.setUser(user);
      orderList.add(order);
    }
    {
      Order order = new Order();
      order.setAmout(200);
      order.setBookingDate(df.parse("2015-07-02"));
      User user = new User();
      user.setLevel(2);
      user.setName("Name2");
      order.setUser(user);
      orderList.add(order);
    }
    {
      Order order = new Order();
      order.setAmout(800);
      order.setBookingDate(df.parse("2015-07-03"));
      User user = new User();
      user.setLevel(3);
      user.setName("Name3");
      order.setUser(user);
      orderList.add(order);
    }
    {
      Order order = new Order();
      order.setAmout(1500);
      order.setBookingDate(df.parse("2015-07-04"));
      User user = new User();
      user.setLevel(4);
      user.setName("Name4");
      order.setUser(user);
      orderList.add(order);
    }
    return orderList;
  }
}
