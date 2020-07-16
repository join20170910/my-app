package com.mycompany.designpatterns;

import com.mycompany.app.common.designpatterns.chain.*;
import org.junit.Test;

import java.beans.Transient;

/**
 * @ClassName DesignPattern
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/16 21:19
 * @Version 1.0
 **/
public class DesignPatterns {


    // 责任链模式
    @Test
    public void chainTest(){

        AbstractSupport alice = new NoSupport("Alice");
        AbstractSupport bob = new LimitSupport("Bob",100);
        AbstractSupport charlie = new SpecialSupport("Charlie",429);
        AbstractSupport diana = new LimitSupport("Diana",200);
        AbstractSupport elmo = new OddSupport("Elmo");
        AbstractSupport fred = new LimitSupport("Fred",300);
        // 形成各种现链
        alice.setNext(bob)
                .setNext(charlie)
                .setNext(diana)
                .setNext(elmo)
                .setNext(fred);

        for ( int i =0; i <500; i+=33){
            alice.support(new Trouble(i));
        }

    }
}
