package com.mycompany.common.designpatterns.chain;

/**
 * @ClassName SpecialSupport 解决指定编号的问题
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/16 21:13
 * @Version 1.0
 **/
public class SpecialSupport extends AbstractSupport{

    //只能解决指定编号 的问题
    private int number;
    public SpecialSupport(String name,int number) {
        super(name);
        this.number = number;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        if (trouble.getNumber() == number){
            return true;
        }
        return false;
    }
}
