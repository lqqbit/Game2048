package cn.lqq.command;

import cn.lqq.GameContext;
import cn.lqq.block.Block;
import cn.lqq.block.BlockCombiner;
import cn.lqq.block.BlockMover;
import cn.lqq.block.Direction;

import java.util.List;

/**
 * Created by lqq on 4/14/15.
 */
public class UpCommand extends ActionCommand {

    public UpCommand(GameContext context) {
        super(context);
    }

    @Override
    protected void combine() {
        int score = context.getScore();
        for (int i = 0; i < 4; i++) {
            List<Block> oneColumnBlocks = context.getBlockCollection().getOneColumnBlocks(i);
            score += BlockCombiner.combine(oneColumnBlocks, Direction.UP);
        }
        context.setScore(score);
    }

    @Override
    protected void move() {
        for (int i = 0; i < 4; i++) {
            List<Block> oneColumnBlocks = context.getBlockCollection().getOneColumnBlocks(i);
            BlockMover.move(oneColumnBlocks, Direction.UP);
        }
    }
}
