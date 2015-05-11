package cn.lqq.block.memoto;

import java.io.Serializable;

/**
 * 方块的备忘录
 *
 * Created by lqq on 4/21/15.
 */
public class BlockMemoto implements Serializable{

    private int[][] values;

    public BlockMemoto(int[][] values){
        this.values = values;
    }

    public int[][] getValues(){
        return values;
    }
}
