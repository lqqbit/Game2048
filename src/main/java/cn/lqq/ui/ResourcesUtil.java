package cn.lqq.ui;

import javax.swing.*;
import java.net.URL;

public class ResourcesUtil {
    public static ImageIcon getImage(String s) {
        URL url = GameUI.class.getClassLoader().getResource(s);
        return new ImageIcon(url);
    }
}