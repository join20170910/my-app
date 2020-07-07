package com.mycompany.app;

import com.mycompany.app.entity.Order;
import com.mycompany.app.entity.PieceCounting;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.io.IOException;

public class TarSingleFileTest {

    private KieSession session;
    @Before
    public void tarFileTest() throws IOException {

        //设置日期格式
        System.setProperty("drools.dateformat","yyyy-MM-dd HH:mm");
        String filePath = "rules//";
        String fileName = "piececounting.drl";
        String path = filePath+ fileName;
        KieFileSystem kieFileSystem = KieServices.Factory.get().newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(path,"UTF-8"));
        final KieRepository kieRepository = KieServices.Factory.get().getRepository();
        kieRepository.addKieModule(new KieModule() {
            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });

        KieBuilder kieBuilder = KieServices.Factory.get().newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        session = KieServices.Factory.get().newKieContainer(kieRepository.getDefaultReleaseId()).newKieSession();

        session.addEventListener(
                new RuleRuntimeEventListener() {
                    public void objectInserted(ObjectInsertedEvent event) {
                        System.out.println("Object inserted \n"
                                + event.getObject().toString());
                    }

                    public void objectUpdated(ObjectUpdatedEvent event) {
                        System.out.println("Object was updated \n"
                                + "new Content \n" + event.getObject().toString());
                    }

                    public void objectDeleted(ObjectDeletedEvent event) {
                        System.out.println("Object retracted \n"
                                + event.getOldObject().toString());
                    }
                }
        );
    }

    @Test
    public void test0(){
        //Fact对象，事实对象
        Order order = new Order();
        order.setOriginalPrice(500d);

        //将Order对象插入到工作内存中
        session.insert(order);

        //激活规则，由Drools框架自动进行规则匹配，如果规则匹配成功，则执行当前规则
        session.fireAllRules();
        System.out.println("优惠后价格："+order.getRealPrice());

    }

    @Test
    public void pieceCountingTest(){

        PieceCounting pieceCounting = new PieceCounting();
        pieceCounting.setPieceRule("1");
        pieceCounting.setTaskType("1");
        pieceCounting.setProvinceLevel("100");
        session.insert(pieceCounting);
        session.fireAllRules();

    }

    @AfterEach
    public void runDispose() {
        session.dispose(); // 释放资源
    }
}
