package cn.lqq.block;

import java.util.Collections;
import java.util.List;

/**
 * 方块的合并，用于移动方块时中心计算各方块的值，以及方块整体移动
 * <p/>
 * Created by lqq on 4/18/15.
 */
public final class BlockCombiner {

    /**
     * 合并方块
     *
     * @param blocks 四个方块的集合
     */
    public static int combine(List<Block> blocks, Direction direction) {
        // 对于一个方块集合来说，向上和向左合并都是从左往右处理即可
        int sum = 0;
        switch (direction) {
            case UP:
                sum = left(blocks);
                break;
            case DOWN:
                sum = right(blocks);
                break;
            case RIGHT:
                sum = right(blocks);
                break;
            case LEFT:
                sum = left(blocks);
                break;

        }
        return sum;
    }

    private static int left(List<Block> blocks) {
        int sum = 0;
        int curValue;
        // 找到第一个非空的方块，然后找到下一个值相同的方块，进行合并，循环到无方块为止
        for (int i = 0; i < blocks.size() - 1; i++) {
            if (blocks.get(i).getValue() == 0) {
                continue;
            }
            curValue = blocks.get(i).getValue();
            for (int j = i + 1; j < blocks.size(); j++) {
                if (blocks.get(j).getValue() == 0) {
                    continue;
                }

                if (blocks.get(j).getValue() == curValue) {
                    sum += blocks.get(i).combine(blocks.get(j));
                }

                break;
            }
        }
        return sum;
    }

    private static int right(List<Block> blocks) {
        Collections.reverse(blocks);
        return left(blocks);
    }

}
