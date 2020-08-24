package ru.begletsov.converter;

import ru.begletsov.frame.FrameSelectParam;
import ru.begletsov.parser.CRC16;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                FileInputStream fileInput = null;
                FileOutputStream fileOutput = null;
                String headerTable = new String("N_Cycle");
                String delimiter = ", ";

                try {
                    for (File filePath: selectedFiles) {
                        try {
                            fileInput = new FileInputStream(filePath);
                            fileOutput = new FileOutputStream("ExistFile.csv");
                            //выделяем память и считываем файл целиком
                            byte[] buffer = new byte[fileInput.available()];
                            fileInput.read(buffer, 0, fileInput.available());
                            //запись шапки таблицы
                            fileOutput.write(headerTable.getBytes());

                            List markerCycleAtFile = new ArrayList();
                            long before = System.currentTimeMillis();
                            for (int index = 0; index < buffer.length - 1; index++) {
                                if (buffer[index] == (byte)0x01 && buffer[index + 1] == (byte)0x01
                                    && index + 55 < buffer.length)
                                {
                                    //1. Выделяем память под обмен БВК: 0х01, 0х01
                                    byte[] bufferAbonent = new byte[55];
                                    int lenBuff = bufferAbonent.length;
                                    bufferAbonent = Arrays.copyOfRange(buffer, index, index + 55);
                                    //2. Считаем CRC
                                    CRC16 crc16 = new CRC16();
                                    crc16.calc(Arrays.copyOfRange(bufferAbonent, 0, bufferAbonent.length - 2));
                                    //3. Сверяем CRC: расчетный и из файла
                                    if (crc16.getHi() == bufferAbonent[lenBuff -2] && crc16.getLow() == bufferAbonent[lenBuff - 1]) {
                                        //4. Добавляем в список начало нового цикла
                                        markerCycleAtFile.add(index - 1);
                                        fileOutput.write(Integer.toString(index).getBytes());
                                        fileOutput.write(delimiter.getBytes());
                                        fileOutput.write(System.lineSeparator().getBytes());
                                    }
                                }
                            }

                            long after = System.currentTimeMillis();
                            long rslt = after - before;
                            System.out.println(rslt);

                        } catch (FileNotFoundException fne) {
                            System.out.println(fne.getMessage());
                        } catch (IOException ioe) {
                            System.out.println(ioe.getMessage());
                        } finally {
                            try {
                                if (fileInput != null) {
                                    fileInput.close();
                                }
                            } catch (IOException exc) {
                                System.out.println("Ошибка при закрытии входного файла: " + exc);
                            }

                            try {
                                if (fileOutput != null) {
                                    fileOutput.close();
                                }
                            } catch (IOException exc) {
                                System.out.println("Ошибка при закрытии выходного файла: " + exc);
                            }
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                } finally {

                }
            }
        });

        int asdasd = 0;
        asdasd++;
    }
}
