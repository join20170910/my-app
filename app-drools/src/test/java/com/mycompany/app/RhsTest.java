package com.mycompany.app;

import com.mycompany.app.bean.NumCount;
import com.mycompany.app.bean.People;
import com.mycompany.app.globa.GloableService;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.kie.api.KieBase;
import org.kie.api.definition.type.FactType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RhsTest {

        @Autowired
        private KieSession session;

        @Autowired
        private KieBase kieBase;

        @Test
        public void update() {

            People people = new People();
            people.setName("达");
            people.setSex(0);
            people.setAge(17);
            people.setDrlType("update");
            session.insert(people);//插入
            session.fireAllRules();//执行规则
            System.out.println("test执行====" + people.toString());
        }

        @Test
        public void impot() {
            People people = new People();
            people.setDrlType("impot");
            session.insert(people);//插入
            session.fireAllRules();//执行规则
        }

        @Test
        public void global() {
            People people = new People();
            people.setDrlType("global");
            session.insert(people);//插入
            //配置全局变量
            List<People> list = new ArrayList<>();
            NumCount numCount = new NumCount();
            GloableService service = new GloableService();
            session.setGlobal("list", list);
            session.setGlobal("numCount", numCount);
            session.setGlobal("service", service);
            session.fireAllRules();//执行规则
            //取出全局变量值
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
        @Test
        public void declare() throws IllegalAccessException, InstantiationException {

            FactType factType = kieBase.getFactType("com.ytooo.declar","Love");
            Object obj = factType.newInstance();
            factType.set(obj,"feel","sad");
            factType.set(obj,"continued","永远");
            session.insert(obj);
            session.fireAllRules();
        }


    @Test
    public void compile() throws IOException {
       /* File file = new File("D:\\iwork-space\\rule\\src\\test\\resources\\ka\\isP4P.xls");
        InputStream is = new FileInputStream(file);
*/

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath*:" + "xls" + "**/*.*");
        System.out.println(resources.length);

        SpreadsheetCompiler converter = new SpreadsheetCompiler();
        for (Resource fileResource : resources) {
            fileResource.getInputStream();
            FileReader fileReader = new FileReader(fileResource.getFile());

            char[] buf = new char[6];
               int len;
                while((len=fileReader.read(buf)) != -1){
                          String str = new String(buf,0,len);
                         System.out.print(str);
                     }
            String drl = converter.compile(fileResource.getInputStream(), InputType.XLS); //--------exception here--------
            System.out.println("\n\n" + drl);
        }

    }

        @AfterEach
        public void runDispose() {
            session.dispose();//释放资源
        }
}
