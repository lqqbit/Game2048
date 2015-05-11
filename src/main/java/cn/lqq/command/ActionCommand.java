package cn.lqq.command;

import cn.lqq.GameContext;
import cn.lqq.block.BlockCollection;

/**
 * 上下左右的动作命令抽象类
 *
 * Created by lqq on 4/18/15.
 */
public abstract class ActionCommand implements Command {
    protected BlockCollection blockCollection;
    protected GameContext context;

    public ActionCommand(GameContext context) {
        this.context = context;
        this.blockCollection = context.getBlockCollection();
    }

    @Override
    public void execute() {
        combine();
        move();
    }

    /**
     * 整体移动方块
     */
    protected abstract void move();

    /**
     * 合并方块
     */
    protected abstract void combine();

}
