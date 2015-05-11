package cn.lqq.command;

import cn.lqq.CommonConstants;
import cn.lqq.GameContext;

import java.awt.event.KeyEvent;

/**
 * Created by lqq on 4/27/15.
 */
public class CommandFactory {

    public static Command getCommand(String code, GameContext context){
        Command cmd;
        if (code == null && code.length() <=0) {
            code = "";
        }
        switch(code){
            case CommonConstants.UP:
                cmd = new UpCommand(context);
                break;
            case CommonConstants.DOWN:
                cmd = new DownCommand(context);
                break;
            case CommonConstants.LEFT:
                cmd = new LeftCommand(context);
                break;
            case CommonConstants.RIGHT:
                cmd = new RightCommand(context);
                break;
            case CommonConstants.UNDO:
                cmd = new UndoCommand(context);
                break;
            case CommonConstants.SAVE:
                cmd = new SaveCommand(context);
                break;
            case CommonConstants.CHECKPOINT:
                cmd = new CheckPointCommand(context);
                break;
            case CommonConstants.RESTORE:
                cmd = new RestoreCommand(context);
                break;
            case CommonConstants.NEW_GAME:
                cmd = new NewGameCommand(context);
                break;
            default:
                cmd = new NullCommond();
        }
        return cmd;
    }

    public static Command getCommand(int code, GameContext context){
        Command cmd;
        switch (code){
            case KeyEvent.VK_LEFT:
                cmd = new LeftCommand(context);
                break;
            case KeyEvent.VK_UP:
                cmd = new UpCommand(context);
                break;
            case KeyEvent.VK_RIGHT:
                cmd = new RightCommand(context);
                break;
            case KeyEvent.VK_DOWN:
                cmd = new DownCommand(context);
                break;
            default:
                cmd = new NullCommond();
        }
        return cmd;
    }
}
