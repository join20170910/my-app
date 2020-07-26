package com.mycompany.app.common.designpatterns.command;

import com.mycompany.app.common.designpatterns.command.interfa.Drawable;

import java.awt.*;

/**
 * @ClassName DrawCanvas
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/16 22:28
 * @Version 1.0
 **/
public class DrawCanvas extends Canvas implements Drawable {

    private Color color = Color.red;
    // 绘制 的圆点的半径
    private int radius = 6;
    // 命令的历史记录
    private MacroCommand history;


    public DrawCanvas(int width,int height,MacroCommand history){
        setSize(width,height);
        setBackground(color);
        this.history = history;
    }
    //重新全部绘制
    public void paint(Graphics g){
        history.execute();
    }
    @Override
    public void draw(int x, int y) {
        Graphics g = getGraphics();
        g.setColor(color);
        g.fillOval(x - radius, y - radius, radius * 2,radius * 2);
    }
}
