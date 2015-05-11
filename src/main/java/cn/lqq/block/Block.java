package cn.lqq.block;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * 移动的方块
 * Created by lqq on 4/14/15.
 */
public class Block extends JButton {

    private static Color c[] = new Color[16];
    int value = 0;

    static {
        c[0] = new Color(200, 200, 200);
        c[1] = new Color(250, 180, 220);
        c[2] = new Color(250, 150, 140);
        c[3] = new Color(250, 220, 140);
        c[4] = new Color(250, 250, 140);
        c[5] = new Color(220, 200, 130);
        c[6] = new Color(190, 220, 130);
        c[7] = new Color(130, 250, 140);
        c[8] = new Color(140, 250, 240);
        c[9] = new Color(140, 220, 240);
        c[10] = new Color(140, 180, 240);
        c[11] = new Color(140, 150, 240);
        c[12] = new Color(180, 140, 240);
        c[13] = new Color(200, 140, 240);
        c[14] = new Color(240, 140, 240);
        c[15] = new Color(250, 0, 0);
    }

    public Block() {
        super();
        setValue(0);
        setFont(new Font("Dialog", Font.BOLD, 24));
        //所有JButton不能设置为焦点，否则一点击按钮就键盘失效
        setFocusable(false);
    }

    /**
     * 初始化当前值
     */
    public void init() {
        Random rnd = new Random(System.currentTimeMillis());
        if (rnd.nextDouble() > 0.6) {
            setValue(4);
        } else {
            setValue(2);
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        if (value == 0) {
            setText("");
        } else {
            setText(String.valueOf(value));
        }
        // 设置背景颜色
        updateBGColor(value);
    }

    /**
     * 两个Block合并
     */
    public int combine(Block block) {
        int ret = 0;
        if (block == null)
            return ret;

        if (this.value == 0) {
            setValue(block.getValue());
            block.setValue(0);
        } else if (this.value == block.getValue()) {
            setValue(this.value * 2);
            block.setValue(0);
            ret = this.value;
        }

        return ret;
    }

    private void updateBGColor(int value) {
        switch (value) {
            case 0:
                setBackground(c[0]);
                break;
            case 2:
                setBackground(c[1]);
                break;
            case 4:
                setBackground(c[2]);
                break;
            case 8:
                setBackground(c[3]);
                break;
            case 16:
                setBackground(c[4]);
                break;
            case 32:
                setBackground(c[5]);
                break;
            case 64:
                setBackground(c[6]);
                break;
            case 128:
                setBackground(c[7]);
                break;
            case 256:
                setBackground(c[8]);
                break;
            case 512:
                setBackground(c[9]);
                break;
            case 1024:
                setBackground(c[10]);
                break;
            case 2048:
                setBackground(c[11]);
                break;
            case 4096:
                setBackground(c[12]);
                break;
            case 8192:
                setBackground(c[13]);
                break;
            case 16384:
                setBackground(c[14]);
                break;
            default:
                setBackground(c[15]);
        }
    }

    public void reset() {
        setBackground(new Color(200, 200, 200));
        setValue(0);
    }

    @Override
    public String toString() {
        return "[value=" + value + "]";
    }
}
