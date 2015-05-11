package cn.lqq.command;

import cn.lqq.GameContext;

/**
 * Created by lqq on 2015/4/27.
 */
public class NewGameCommand implements Command {

    private GameContext context;

    public NewGameCommand(GameContext context){
        this.context = context;
    }

    @Override
    public void execute() {
        context.getGame().restart();
    }
}
