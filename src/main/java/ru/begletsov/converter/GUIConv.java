package ru.begletsov.converter;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.File;

public class GUIConv extends JFrame {
    private JLabel text1;
    private JFileChooser fileChooser;

    public GUIConv() {
        super("Заголовок окна");
        setLayout(new FlowLayout());
        text1 = new JLabel("Пробный текст");
        text1.setToolTipText("Просто так");
        add(text1);

        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            //какие-то действия
            int aaa = 0;
        }

    }
}
