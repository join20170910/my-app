package com.mycompany.app.common.designpatterns.chain;

/**
 * @ClassName Trouble  发生的问题类
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/16 20:44
 * @Version 1.0
 **/
public class Trouble {

    //问题编号
    private int number;
    public Trouble(int number){
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Trouble{" +
                "number=" + number +
                '}';
    }


}
