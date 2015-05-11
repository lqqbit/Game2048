package cn.lqq.block;

import java.util.Collections;
import java.util.List;

/**
 * 方块整体移动
 * <p/>
 * Created by lqq on 4/18/15.
 */
public class BlockMover {
    /**
     * 整体移动合并后的方块
     */
    public static void move(List<Block> blocks, Direction direction) {
        switch (direction) {
            case UP:
                leftMove(blocks);
                break;
            case DOWN:
                rightMove(blocks);
                break;
            case RIGHT:
                rightMove(blocks);
                break;
            case LEFT:
                leftMove(blocks);
                break;

        }
    }


    private static void leftMove(List<Block> blocks) {
        for (int i = 1; i < blocks.size(); i++) {
            if (blocks.get(i).getValue() == 0) {
                continue;
            }

            // 往前找到第一个非零的位置
            for (int j = i - 1; j >= 0; j--) {
                if (blocks.get(j).getValue() == 0 && j == 0) {
                    blocks.get(j).combine(blocks.get(i));
                }

                if (blocks.get(j).getValue() == 0) {
                    continue;
                }

                if (i - j > 1) {
                    blocks.get(j + 1).combine(blocks.get(i));
                }
            }
        }
    }

    private static void rightMove(List<Block> blocks) {
        Collections.reverse(blocks);
        leftMove(blocks);
    }
}
