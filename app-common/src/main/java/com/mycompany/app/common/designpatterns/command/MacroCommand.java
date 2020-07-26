package com.mycompany.app.common.designpatterns.command;

import com.mycompany.app.common.designpatterns.command.interfa.Command;

import java.util.Iterator;
import java.util.Stack;

/**
 * @ClassName MacroCommand  多命令集合
 * @Deacription TODO
 * @Author apple
 * @Date 2020/7/16 22:12
 * @Version 1.0
 **/
public class MacroCommand implements Command {

    //命令集合
    private Stack<Command> commands = new Stack();

    // 执行命令
    @Override
    public void execute() {
       Iterator<Command> it = commands.iterator();
       while (it.hasNext()){
           it.next().execute();
       }
    }
    // 添加 命令
    public void append(Command command){
        if(command != this){
            commands.push(command);
        }
    }
    // 删除最后 一条命令
    public void undo(){
        if(!commands.empty()){
            commands.pop();
        }
    }
    // 删除所有命令
    public void clear(){
        commands.clear();
    }
}
