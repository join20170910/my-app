package com.mycompany.common.designpatterns.chain;

/**
 * @ClassName OddSupport 解决奇数编号的问题
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/16 21:11
 * @Version 1.0
 **/
public class OddSupport extends AbstractSupport{

    public OddSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {

        if (trouble.getNumber() %2 ==1 ){
            return true;
        }
        return false;
    }

}
