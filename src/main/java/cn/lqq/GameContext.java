package cn.lqq;

import cn.lqq.block.BlockCollection;
import cn.lqq.ui.GameUI;

/**
 * 游戏运行上下文
 * <p/>
 * Created by lqq on 4/14/15.
 */
public class GameContext {

    private int score = 0;

    private int stepCounter = 0;

    private transient BlockCollection blockCollection;

    private transient GameUI game;

    /**
     * 当前得分
     */
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 当前最大方块值
     */
    public int getMaxValue() {
        return blockCollection.getMaxValue();
    }

    /**
     * 步数
     */
    public int getStepCounter() {
        return stepCounter;
    }

    public void setStepCounter(int stepCounter) {
        this.stepCounter = stepCounter;
    }

    public BlockCollection getBlockCollection() {
        return blockCollection;
    }

    public void setBlockCollection(BlockCollection blockCollection) {
        this.blockCollection = blockCollection;
    }

    public GameUI getGame() {
        return game;
    }

    public void setGame(GameUI game) {
        this.game = game;
    }
}
