package cn.lqq.command;

import cn.lqq.GameContext;
import cn.lqq.block.BlockCollection;
import cn.lqq.block.memoto.MemotoTaker;

/**
 * Created by lqq on 4/27/15.
 */
public class UndoCommand implements Command{

    private GameContext context;

    public UndoCommand(GameContext context){
        this.context = context;
    }

    @Override
    public void execute() {
        BlockCollection blockCollection = context.getBlockCollection();
        blockCollection.restoreMemoto(MemotoTaker.restoreMemoto());
    }
}
