package cn.lqq.command;

import cn.lqq.GameContext;
import cn.lqq.block.memoto.BlockMemoto;
import cn.lqq.block.memoto.MemotoTaker;

import javax.swing.*;
import java.io.File;

/**
 * Created by lqq on 4/27/15.
 */
public class SaveCommand implements Command {

    private GameContext context;

    public SaveCommand(GameContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        BlockMemoto memoto = context.getBlockCollection().createMemoto();
        JFileChooser dlg = new JFileChooser();
        dlg.setDialogTitle("选择");
        int result = dlg.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = dlg.getSelectedFile();
            MemotoTaker.saveMemoto(memoto, file);
        }
    }
}
