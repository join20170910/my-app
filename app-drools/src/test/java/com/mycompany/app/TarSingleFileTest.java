package com.mycompany.app;

import com.mycompany.app.bean.CreditCardApplyInfo;
import com.mycompany.app.bean.cal.Calculation;
import com.mycompany.app.entity.ComparisonOperatorEntity;
import com.mycompany.app.entity.Order;
import com.mycompany.app.entity.PieceCounting;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.drools.core.event.DebugAgendaEventListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.event.rule.*;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.io.ResourceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.app.entity.Student;

public class TarSingleFileTest {

  private KieSession session;
  private KieRuntimeLogger logger;

  @Before
  public void tarFileTest() throws IOException {

    // 设置日期格式
    System.setProperty("drools.dateformat", "yyyy-MM-dd");
    String filePath = "rules//";
    String fileName = "creditCardApply.drl";
    String path = filePath + fileName;
    KieFileSystem kieFileSystem = KieServices.Factory.get().newKieFileSystem();
    kieFileSystem.write(ResourceFactory.newClassPathResource(path, "UTF-8"));
    final KieRepository kieRepository = KieServices.Factory.get().getRepository();
    kieRepository.addKieModule(
        new KieModule() {
          @Override
          public ReleaseId getReleaseId() {
            return kieRepository.getDefaultReleaseId();
          }
        });

    KieBuilder kieBuilder = KieServices.Factory.get().newKieBuilder(kieFileSystem);
    kieBuilder.buildAll();
    session =
        KieServices.Factory.get()
            .newKieContainer(kieRepository.getDefaultReleaseId())
            .newKieSession();

    session.addEventListener(
        new RuleRuntimeEventListener() {
          public void objectInserted(ObjectInsertedEvent event) {
            System.out.println("Object inserted \n" + event.getObject().toString());
          }

          public void objectUpdated(ObjectUpdatedEvent event) {
            System.out.println(
                "Object was updated \n" + "new Content \n" + event.getObject().toString());
          }

          public void objectDeleted(ObjectDeletedEvent event) {
            System.out.println("Object retracted \n" + event.getOldObject().toString());
          }
        });

    session.addEventListener(new DebugAgendaEventListener());
    session.addEventListener(new DebugRuleRuntimeEventListener());
    logger = KieServices.get().getLoggers().newFileLogger(session, "./target/calculation");
  }

  @Test
  public void test0() {
    // Fact对象，事实对象
    Order order = new Order();
    order.setOriginalPrice(500D);

    // 将Order对象插入到工作内存中
    session.insert(order);

    // 激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
    session.fireAllRules();
    System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
  }

  @Test
  public void pieceCountingTest() {

    PieceCounting pieceCounting = new PieceCounting();
    pieceCounting.setPieceRule("1");
    pieceCounting.setTaskType("1");
    pieceCounting.setProvinceLevel("100");
    session.insert(pieceCounting);
    session.fireAllRules();
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
    session.insert(comparisonOperatorEntity);
    session.fireAllRules();
  }

  @Test
  public void comparisonOperatorTest01() {
    ComparisonOperatorEntity comparisonOperatorEntity = new ComparisonOperatorEntity();

    comparisonOperatorEntity.setNames("张三");

    List<String> list = new ArrayList<String>();

    list.add("张三");

    list.add("李四");

    comparisonOperatorEntity.setList(list);

    session.insert(comparisonOperatorEntity);

    // 通过规则过滤器实现只执行指定规则

    session.fireAllRules(new RuleNameEqualsAgendaFilter("rule_comparison_memberOf"));
  }

  @Test
  public void studentTest() {

    Student student = new Student();
    student.setAge(5);
    student.setName("张三");
    // 将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配，如果规则匹配成功则执行规则
    session.insert(student);
    session.fireAllRules();
  }

  @Test
  public void student_insertTest() {
    Student student = new Student();
    student.setAge(10);
    student.setName("张三");
    // 将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配，如果规则匹配成功则执行规则
    session.insert(student);
    session.fireAllRules();
  }

  // retract方法的作用是删除工作内存中的数据，并让相关的规则重新匹配。
  @Test
  public void student_retractTest() {

    Student student = new Student();
    student.setAge(10);
    student.setName("张三");
    // 将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配，如果规则匹配成功则执行规则
    session.insert(student);
    session.fireAllRules();
  }

  // rule_noloop通过控制台可以看到，由于我们没有设置no-loop属性的值，所以发生了死循环。
  // 接下来设置no-loop的值为true再次测试则不会发生死循环。
  @Test
  public void rule_noloopTest() {
    Student student = new Student();
    student.setAge(25);
    student.setName("张三");
    // 将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配，如果规则匹配成功则执行规则
    session.insert(student);
    session.fireAllRules();
  }

  @Test
  public void testactivationgroupTest() {
    session.fireAllRules();
    session.fireUntilHalt();
    session.halt();
  }

  @Test

  public void dateeffectiveTest() {
    // 设置日期格式
    System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm");
    session.fireAllRules();
  }

  @Test
  public void dateexpiresTest() {

    // 设置日期格式
    System.setProperty("drools.dateformat", "yyyy-MM-dd HH:mm");
    session.fireAllRules();
  }

  // 测试 exist
  // 当向Working Memory中加入多个满足条件的Fact对象时，
  // 使用了exists的规则执行一次，不使用exists的规则会执行多次。
  @Test
  public void existsStudentTest() {
    session.insert(new Student());
    session.insert(new Student());
    session.insert(new Student());
    session.insert(new Student());
    session.fireAllRules();
  }

  @Test
  public void calculationTest() {

    Calculation calculation = new Calculation();
    calculation.setWage(5000);
    FactHandle factHandle = session.insert(calculation);
    // factHandle.toExternalForm();
    session.fireAllRules();
    System.out.println(factHandle.toExternalForm());
    System.out.println(calculation);
  }

  @Test
  public void creditCardApplyTest() {

    CreditCardApplyInfo creditCardApplyInfo = new CreditCardApplyInfo();
      creditCardApplyInfo.setEducation(CreditCardApplyInfo.EDUCATION_4);
      creditCardApplyInfo.setHasCar(true);
      creditCardApplyInfo.setHasHouse(true);
      creditCardApplyInfo.setMonthlyIncome(50000);
    FactHandle factHandle = session.insert(creditCardApplyInfo);

    session.fireAllRules();
    System.out.println("-----------------------------");
    System.out.println(creditCardApplyInfo.toString());
  }

  @AfterEach
  public void runDispose() {
    session.dispose(); // 释放资源
    logger.close();
  }
}
