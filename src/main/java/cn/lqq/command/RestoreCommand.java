package cn.lqq.command;

import cn.lqq.GameContext;
import cn.lqq.block.memoto.BlockMemoto;
import cn.lqq.block.memoto.MemotoTaker;

import javax.swing.*;
import java.io.File;

/**
 * Created by lqq on 4/27/15.
 */
public class RestoreCommand implements Command{

    private GameContext context;

    public RestoreCommand(GameContext context){
        this.context = context;
    }

    @Override
    public void execute() {
        JFileChooser dlg = new JFileChooser();
        dlg.setDialogTitle("选择");
        int result = dlg.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = dlg.getSelectedFile();
            BlockMemoto memoto = MemotoTaker.restoreMemoto(file);
            context.getBlockCollection().restoreMemoto(memoto);
        }
    }
}
