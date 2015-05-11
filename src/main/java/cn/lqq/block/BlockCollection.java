package cn.lqq.block;

import cn.lqq.block.memoto.BlockMemoto;

import java.util.*;

/**
 * 方块元素的管理类，负责按钮的初始化
 * Created by lqq on 4/14/15.
 */
public class BlockCollection implements Iterable<Block> {
    public static final int LENGTH = 4;
    private Block[][] jbtn = new Block[LENGTH][LENGTH];
    /**
     * 记录当前方块最大值
     */
    private int maxValue;

    public BlockCollection() {
        maxValue = 0;
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                jbtn[i][j] = new Block();
                // FIXME:这个代码不应该存在这里的，不需要管界面布局
                jbtn[i][j].setBounds(100 * i, 100 * j, 100, 100);
            }
        }
        initBlock();
    }

    public int getMaxValue() {
        return maxValue;
    }

    /**
     * 随机生成一个方块
     */
    public boolean nextBlock() {
        Random rnd = new Random(System.currentTimeMillis());
        List<Block> zeroBlock = new ArrayList<>();
        for (Block block : this) {
            if (block.getValue() == 0)
                zeroBlock.add(block);
        }
        if (zeroBlock.size() <= 0) return false;
        zeroBlock.get(rnd.nextInt(zeroBlock.size())).init();
        return true;
    }

    /**
     * 创建备忘录对象
     */
    public BlockMemoto createMemoto() {
        return new BlockMemoto(getAllBlockValue());
    }

    public void restoreMemoto(BlockMemoto memoto) {
        restoreBlock(memoto.getValues());
    }

    /**
     * 获取方块的值，用于备份
     */
    private int[][] getAllBlockValue() {
        int[][] ret = new int[LENGTH][LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                ret[i][j] = jbtn[i][j].getValue();
            }
        }
        return ret;
    }

    /**
     * 根据值恢复方块
     */
    private void restoreBlock(int[][] values) {
        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < LENGTH; j++) {
                jbtn[i][j].setValue(values[i][j]);
            }
        }
    }

    /**
     * 初始化方块
     */
    private void initBlock() {
        // 默认生成4个Block
        Random rnd = new Random(System.currentTimeMillis());
        Set<Block> blackList = new HashSet<>();
        for (int i = 0; i < LENGTH - 1; i++) {
            int _row = rnd.nextInt(LENGTH);
            int _col = rnd.nextInt(LENGTH);

            // 生成过的pass
            if (blackList.contains(jbtn[_row][_col])) {
                i--;
                continue;
            }
            jbtn[_row][_col].init();
            blackList.add(jbtn[_row][_col]);
        }

    }

    public void setBlockValue(int row, int col, int value) {
        jbtn[row][col].setValue(value);
        if (value > maxValue) {
            maxValue = value;
        }
    }

    public int getBlockvalue(int row, int col) {
        return jbtn[row][col].getValue();
    }

    /**
     * 获取整行方块
     *
     * @param row 行号，从0开始
     */
    public List<Block> getOneRowBlocks(int row) {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < LENGTH; i++) {
            blocks.add(jbtn[i][row]);
        }
        return blocks;
    }

    /**
     * 获取整列方块
     *
     * @param column 列号，从0开始
     * @return
     */
    public List<Block> getOneColumnBlocks(int column) {
        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < LENGTH; i++) {
            blocks.add(jbtn[column][i]);
        }
        return blocks;
    }

    public boolean isCanCombine() {
        // 遍历前三行列方块
        // 1.看值是否和右下方相同（不需要看左边和上边是因为前一个方块已经判断了）
        // 2.如果右方或者下方都没有方块的话也能移动
        boolean isCanCombine = false;
        for (int i = 0; i < LENGTH - 1; i++) {
            for (int j = 0; j < LENGTH - 1; j++) {
                // 当前方块为0或者右边和下边有相同值，就能合并
                if (jbtn[i][j].getValue() == 0
                        || jbtn[i][j].getValue() == jbtn[i + 1][j].getValue()
                        || jbtn[i][j].getValue() == jbtn[i][j + 1].getValue()) {
                    isCanCombine = true;
                    break;
                } else if (jbtn[i + 1][j].getValue() == 0 || jbtn[i][j + 1].getValue() == 0) {
                    isCanCombine = true;
                    break;
                }
            }
        }
        return isCanCombine;
    }

    @Override
    public Iterator<Block> iterator() {
        return new Iterator<Block>() {
            int _row = 0;
            int _col = 0;

            @Override
            public boolean hasNext() {
                return _row < LENGTH;
            }

            @Override
            public String toString() {
                return super.toString();
            }

            @Override
            public Block next() {
                Block b = jbtn[_row][_col];
                if (++_col >= LENGTH) {
                    ++_row;
                    _col = 0;
                }
                return b;
            }
        };
    }

    public void reset() {
        maxValue = 0;
        for (Block block : this) {
            block.setValue(0);
        }
        initBlock();
    }
}
