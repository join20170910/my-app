package com.mycompany.app.common.designpatterns.chain;

/**
 * @ClassName AbstractSupport 解决问题的抽象类
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/16 20:47
 * @Version 1.0
 **/
public abstract class AbstractSupport {
    //解决问题的实例的名字
    private String name;
    //要推卸给的对象
    private AbstractSupport next;

    //生成 解决问题的步骤
    public AbstractSupport(String name){
        this.name = name;
    }
    //设置要推卸给的对象
    public AbstractSupport setNext(AbstractSupport next){
        this.next = next;
        return next;
    }

    public final void support(Trouble trouble){
        if(resolve(trouble)){
            done(trouble);
        }else if (next != null){
            next.support(trouble);
        }else {
            fail(trouble);
        }
    }

    // 未解决
    protected void fail(Trouble trouble){
        System.out.println(trouble + " cannot be resolved.");
    }

    // 解决
    protected void done(Trouble trouble) {
        System.out.println(trouble + "is resolved by " + this + ".");
    }

    // 解决问题的方法
    protected abstract boolean resolve(Trouble trouble);

    //显示字符串
    @Override
    public String toString() {
        return "Support{" +
                "name='" + name + '\'' +
                '}';
    }
}
