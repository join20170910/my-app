package com.mycompany.common.designpatterns.chain;

/**
 * @ClassName NoSupport 不能解决问题的类
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/16 20:59
 * @Version 1.0
 **/
public class NoSupport extends AbstractSupport{

    public NoSupport(String name) {
        super(name);
    }

    // 解决问题的方法
    // 自己什么也不处理
    @Override
    protected boolean resolve(Trouble trouble) {
        return false;
    }
}
