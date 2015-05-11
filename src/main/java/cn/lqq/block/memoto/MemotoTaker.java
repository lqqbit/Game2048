package cn.lqq.block.memoto;

import java.io.File;
import java.util.Stack;

/**
 * 备忘录的存取者
 * Created by lqq on 4/21/15.
 */
public class MemotoTaker {
    /**内存的备份数据*/
    private static Stack<BlockMemoto> memotos = new Stack<>();

    public static void saveMemoto(BlockMemoto memoto){
        memotos.push(memoto);
        MemotoPersister.persist(memotos.peek());
    }

    public static BlockMemoto restoreMemoto(){
        if(memotos.size() <= 0){
            memotos.add(MemotoPersister.unpersist());
        }
        return memotos.pop();
    }

    public static void saveMemoto(BlockMemoto memoto, File file){
        memotos.push(memoto);
        MemotoPersister.persist(memotos.peek(), file);
    }

    public static BlockMemoto restoreMemoto(File file){
        return MemotoPersister.unpersist(file);
    }

}
