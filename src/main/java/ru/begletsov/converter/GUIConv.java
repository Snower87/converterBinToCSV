package ru.begletsov.converter;

import ru.begletsov.frame.FrameSelectParam;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

public class GUIConv extends JFrame {
    private JLabel text1;
    private JFileChooser fileChooser;
    private JButton buttonOpenFileChoser;
    private JButton buttonSelectParam;
    private File[] selectedFiles;
    private File directory;
    private FrameSelectParam frameSelectParam;

    public GUIConv() {
        super("Конвертер Bin -> CSV файлов");
        setLayout(new FlowLayout());
        text1 = new JLabel("Пробный текст");
        text1.setToolTipText("Просто так");
        buttonOpenFileChoser = new JButton("Выберите файлы");
        buttonSelectParam = new JButton("Выберите параметры");
        frameSelectParam = new FrameSelectParam();
        add(buttonOpenFileChoser);
        add(buttonSelectParam);
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

        buttonSelectParam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameSelectParam.setVisible(true);
            }
        });

        int asdasd = 0;
        asdasd++;
    }
}
