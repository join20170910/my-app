package com.mycompany.app.common.designpatterns.command;

import com.mycompany.app.common.designpatterns.command.entity.Point;
import com.mycompany.app.common.designpatterns.command.interfa.Command;
import com.mycompany.app.common.designpatterns.command.interfa.Drawable;

/**
 * @ClassName DrawCommand  绘制一个点的命令
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/16 22:18
 * @Version 1.0
 **/
public class DrawCommand implements Command {

    //绘制对象
    protected Drawable drawable;
    // 绘制位置
    private Point position;

    public DrawCommand(Drawable drawable, Point position){
        this.drawable =drawable;
        this.position =position;
    }
    @Override
    public void execute() {
        drawable.draw(position.x,position.y);
    }
}
