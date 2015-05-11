package cn.lqq.block.memoto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 备忘录的持久化类，用于保存当前游戏状态
 * Created by lqq on 4/21/15.
 */
class MemotoPersister {

    public static final String CHECKPOINT_PATH = ".";
    public static final String PLAN_NAME = "plan.dat";

    private static final Logger logger = LoggerFactory.getLogger(MemotoPersister.class);

    public static BlockMemoto unpersist(File file) {
        logger.info("Begin to unpersist " + file.lastModified());
        BlockMemoto memoto = null;
        ObjectInputStream oin = null;
        try {
            oin = new ObjectInputStream(new FileInputStream(file));
            memoto = (BlockMemoto) oin.readObject();
            oin.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (oin != null) {
                try {
                    oin.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return memoto;
    }

    public static BlockMemoto unpersist() {
        File file = new File(CHECKPOINT_PATH, PLAN_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return unpersist(file);
    }

    public static void persist(BlockMemoto memoto) {
        File file = new File(CHECKPOINT_PATH, PLAN_NAME);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        persist(memoto, file);
    }

    public static void persist(BlockMemoto memoto, File file) {
        if (memoto == null) return;
        ObjectOutputStream oout = null;
        try {
            oout = new ObjectOutputStream(new FileOutputStream(file));
            oout.writeObject(memoto);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oout != null) {
                try {
                    oout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
