package cn.lqq.ui;

import cn.lqq.CommonConstants;
import cn.lqq.GameContext;
import cn.lqq.block.Block;
import cn.lqq.block.BlockCollection;
import cn.lqq.command.ActionCommand;
import cn.lqq.command.Command;
import cn.lqq.command.CommandFactory;
import cn.lqq.listener.GameListener;

import javax.swing.*;
import java.awt.*;

public class GameUI extends JFrame {

    /**
     * 初始化界面元素
     */
    JLabel status = new JLabel("步数:0步");
    JLabel score = new JLabel("得分:0分");
    JLabel bigS = new JLabel("最大数字");
    ImageIcon iup = ResourcesUtil.getImage("up.png");
    JButton up = new JButton(iup);
    ImageIcon idown = ResourcesUtil.getImage("down.png");
    JButton down = new JButton(idown);
    ImageIcon ileft = ResourcesUtil.getImage("left.png");
    JButton left = new JButton(ileft);
    ImageIcon iright = ResourcesUtil.getImage("right.png");
    JButton right = new JButton(iright);
    JButton undo = new JButton("撤销");
    ImageIcon isave = ResourcesUtil.getImage("save.png");
    JButton save = new JButton(isave);
    ImageIcon irec = ResourcesUtil.getImage("rec.png");
    JButton rec = new JButton(irec);
    ImageIcon inew = ResourcesUtil.getImage("new.png");
    JButton newGame = new JButton(inew);

    GameContext context = null;
    GameListener listener = null;
    BlockCollection blockCollection = new BlockCollection();

    public GameUI() {
        super();
        context = new GameContext();
        context.setBlockCollection(blockCollection);
        context.setGame(this);
        listener = new GameListener(context);
    }

    public static void main(String[] args) {
        GameUI gameUI = new GameUI();
        gameUI.init();
        gameUI.setVisible(true);
        gameUI.start();
        gameUI.setFocusable(true);
    }

    /**
     * 初始化游戏
     */
    public void initGame() {
        blockCollection.reset();
        context.setBlockCollection(blockCollection);
        context.setScore(0);
        context.setStepCounter(0);
        updateScoreBoard();
        CommandFactory.getCommand(CommonConstants.CHECKPOINT, context).execute();
    }

    public boolean isGameOver() {
        if (!blockCollection.isCanCombine()) {
            JOptionPane.showMessageDialog(null, "你输了！");
            restart();
            updateScoreBoard();
            return true;
        }
        return false;
    }

    /**
     * 执行命令
     */
    public void executeCommand(Command cmd) {
        boolean subClassOfActionCommand = ActionCommand.class.isAssignableFrom(cmd.getClass());
        if (subClassOfActionCommand) {
            // 每次执行命令前都主动做一次CheckPoint
            CommandFactory.getCommand(CommonConstants.CHECKPOINT, context).execute();
        }
        cmd.execute();
        if (subClassOfActionCommand) {
            next();
        }
    }

    /**
     * 继续下一步操作
     */
    public void next() {
        // 生成新的数字
        blockCollection.nextBlock();
        updateScoreBoard();
        // 如果达到2048，弹出提示框
        if (blockCollection.getMaxValue() == 2048) {
            JOptionPane.showMessageDialog(null, "你赢了！");
        }
    }

    /**
     * 重新开始
     */
    public void restart() {
        initGame();
    }

    public final void init() {
        this.setSize(560, 450);
        this.setTitle("2048");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBackground(Color.gray);
        this.setResizable(false);
        //设置窗体显示在屏幕中间
        this.setLocationRelativeTo(null);
        //获得窗体的内容区域
        this.setLayout(null);
        initWidget();
        //增加键盘和窗口的监听器
        this.addKeyListener(listener);
        this.addWindowListener(listener);
    }

    /**
     * 初始化按钮
     */
    private void initWidget() {
        for (Block block : blockCollection) {
            this.add(block);
        }
        up.setBounds(450, 250, 48, 48);
        down.setBounds(450, 350, 48, 48);
        left.setBounds(400, 300, 48, 48);
        right.setBounds(500, 300, 48, 48);
        undo.setBounds(420, 150, 80, 30);
        save.setBounds(420, 190, 30, 30);
        rec.setBounds(460, 190, 30, 30);
        newGame.setBounds(500, 190, 30, 30);
        up.setContentAreaFilled(false);
        down.setContentAreaFilled(false);
        left.setContentAreaFilled(false);
        right.setContentAreaFilled(false);
        undo.setContentAreaFilled(false);
        save.setContentAreaFilled(false);
        rec.setContentAreaFilled(false);
        newGame.setContentAreaFilled(false);
        up.setActionCommand(CommonConstants.UP);
        down.setActionCommand(CommonConstants.DOWN);
        left.setActionCommand(CommonConstants.LEFT);
        right.setActionCommand(CommonConstants.RIGHT);
        undo.setActionCommand(CommonConstants.UNDO);
        save.setActionCommand(CommonConstants.SAVE);
        rec.setActionCommand(CommonConstants.RESTORE);
        newGame.setActionCommand(CommonConstants.NEW_GAME);
        up.addActionListener(listener);
        down.addActionListener(listener);
        left.addActionListener(listener);
        right.addActionListener(listener);
        undo.addActionListener(listener);
        save.addActionListener(listener);
        rec.addActionListener(listener);
        newGame.addActionListener(listener);
        this.add(up);
        this.add(down);
        this.add(left);
        this.add(right);
        this.add(undo);
        this.add(save);
        this.add(rec);
        this.add(newGame);

        up.setFocusable(false);
        down.setFocusable(false);
        left.setFocusable(false);
        right.setFocusable(false);
        undo.setFocusable(false);
        save.setFocusable(false);
        rec.setFocusable(false);
        newGame.setFocusable(false);

        status.setBounds(420, 30, 100, 30);
        score.setBounds(420, 70, 100, 30);
        bigS.setBounds(420, 110, 100, 30);
        this.add(status);
        this.add(score);
        this.add(bigS);
    }

    /**
     * 开始游戏
     */
    public void start() {
        initGame();
    }

    public void updateScoreBoard() {
        score.setText("得分:" + context.getScore() + "分");
        bigS.setText("最大数字:" + blockCollection.getMaxValue());
        status.setText("步数:" + context.getStepCounter() + "步");
    }

}
