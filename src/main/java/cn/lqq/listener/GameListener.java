package cn.lqq.listener;

import cn.lqq.CommonConstants;
import cn.lqq.GameContext;
import cn.lqq.command.Command;
import cn.lqq.command.CommandFactory;
import cn.lqq.ui.GameUI;

import java.awt.event.*;

/**
 * 监听键盘和按钮事件
 */
public class GameListener extends WindowAdapter implements ActionListener, KeyListener{

    private GameContext context;
    private GameUI game;

    public GameListener(GameContext context) {
        this.context = context;
        this.game = context.getGame();
    }

    public void actionPerformed(ActionEvent e) {
        String source = e.getActionCommand();
        if (game.isGameOver()) return;
        Command cmd = CommandFactory.getCommand(source, context);
        game.executeCommand(cmd);
    }

    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        if (game.isGameOver()) return;
        Command cmd = CommandFactory.getCommand(keycode, context);
        game.executeCommand(cmd);
    }

    public void windowClosing(WindowEvent e) {
        // 关闭窗口是保存进度
        Command cmd = CommandFactory.getCommand(CommonConstants.CHECKPOINT, context);
        game.executeCommand(cmd);
    }

    public void keyPressed(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {
    }

}
