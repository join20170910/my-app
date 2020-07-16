package com.mycompany.app.common.designpatterns.chain;

/**
 * @ClassName LimitSupport 解决编号 小于limit 值的问题
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/16 21:01
 * @Version 1.0
 **/
public class LimitSupport extends AbstractSupport{

    //可以解决编号小于的 limit 问题
    private int limit;
    public LimitSupport(String name,int limit) {
        super(name);
        this.limit = limit;
    }

    @Override
    protected boolean resolve(Trouble trouble) {

        if (trouble.getNumber() <limit ){
            return true;
        }
        return false;
    }
}
