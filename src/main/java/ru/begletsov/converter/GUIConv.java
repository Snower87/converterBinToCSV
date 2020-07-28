package ru.begletsov.converter;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUIConv extends JFrame {
    private JLabel text1;
    private JFileChooser fileChooser;
    private JButton buttonOpenFileChoser;
    private File[] selectedFiles;
    private File directory;

    public GUIConv() {
        super("Конвертер Bin -> CSV файлов");
        setLayout(new FlowLayout());
        text1 = new JLabel("Пробный текст");
        text1.setToolTipText("Просто так");
        buttonOpenFileChoser = new JButton("Выберите файлы");
        add(buttonOpenFileChoser);
        add(text1);

        //fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileHidingEnabled(false);
        fileChooser.setMultiSelectionEnabled(true);

        buttonOpenFileChoser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (fileChooser.showDialog(null, "Открыть")) {
                    case  JFileChooser.APPROVE_OPTION:
                        selectedFiles = fileChooser.getSelectedFiles();
                        directory = fileChooser.getCurrentDirectory();
                        break;
                    case JFileChooser.CANCEL_OPTION:
                        int aaa = 10;
                        break;
                    default:
                        break;
                }


            }
        });





        int asdasd = 0;
        asdasd++;
    }
}
