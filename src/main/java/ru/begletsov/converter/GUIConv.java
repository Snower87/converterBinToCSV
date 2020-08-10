package ru.begletsov.converter;

import ru.begletsov.frame.FrameSelectParam;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;

public class GUIConv extends JFrame {
    private JLabel text1;
    private JFileChooser fileChooser;
    private JButton buttonOpenFileChoser;
    private JButton buttonSelectParam;
    private JButton buttonParseFile;
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
        buttonParseFile = new JButton("Конвертировать");
        frameSelectParam = new FrameSelectParam();
        add(buttonOpenFileChoser);
        add(buttonSelectParam);
        add(buttonParseFile);
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

        buttonParseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream();

                try(FileInputStream InputFile = new FileInputStream(selectedFiles[0])) {

                    long before = System.currentTimeMillis();
                    byte[] buffer = new byte[InputFile.available()];
                    // считаем файл в буфер
                    InputFile.read(buffer, 0, InputFile.available());

                    ArrayList markerCycleAtFile = new ArrayList();

                    for (int index = 1; index < buffer.length; index++) {
                        if (buffer[index-1] == (byte)0x01 && buffer[index] == (byte)0x01) {
                            markerCycleAtFile.add(index - 1);
                        }
                    }
                    long after = System.currentTimeMillis();
                    long rslt = after - before; //35 ms, 90 ms, 135 ms

                    int a = 10;
                } catch (IOException ioe) {
                    System.out.println(ioe.getMessage());
                } finally {
                    //close open file
                }

            }
        });

        int asdasd = 0;
        asdasd++;
    }
}
