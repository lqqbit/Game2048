package cn.lqq.command;

import cn.lqq.GameContext;
import cn.lqq.block.memoto.BlockMemoto;
import cn.lqq.block.memoto.MemotoTaker;

/**
 * Created by lqq on 2015/4/27.
 */
public class CheckPointCommand implements Command {

    private GameContext context;

    public CheckPointCommand(GameContext context){
        this.context = context;
    }

    @Override
    public void execute() {
        BlockMemoto memoto = context.getBlockCollection().createMemoto();
        MemotoTaker.saveMemoto(memoto);
    }
}
