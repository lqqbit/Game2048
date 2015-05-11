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
public class LeftCommand extends ActionCommand {

    public LeftCommand(GameContext context) {
        super(context);
    }

    @Override
    protected void combine() {
        int score = context.getScore();
        for (int i = 0; i < 4; i++) {
            List<Block> oneRowBlocks = context.getBlockCollection().getOneRowBlocks(i);
            score += BlockCombiner.combine(oneRowBlocks, Direction.LEFT);
        }
        context.setScore(score);
    }

    @Override
    protected void move() {
        int score = context.getScore();
        for (int i = 0; i < 4; i++) {
            List<Block> oneRowBlocks = context.getBlockCollection().getOneRowBlocks(i);
            BlockMover.move(oneRowBlocks, Direction.LEFT);
        }
        context.setScore(score);
    }
}
