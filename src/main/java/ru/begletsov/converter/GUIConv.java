package ru.begletsov.converter;

import ru.begletsov.frame.FrameSelectParam;
import ru.begletsov.parser.CRC16;
import ru.begletsov.protocol.Abonent;
import ru.begletsov.protocol.Param;
import ru.begletsov.protocol.Service;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.*;
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
    private Service serviceFromFrameSelectParam;
    private List<Param> listChosenParamsFromFrameSelectParam;

    public GUIConv() {
        super("Конвертер Bin -> CSV файлов");
        setLayout(new FlowLayout());
        text1 = new JLabel("Пробный текст");
        text1.setToolTipText("Просто так");
        buttonOpenFileChoser = new JButton("Выберите файлы");
        buttonSelectParam = new JButton("Выберите параметры");
        buttonParseFile = new JButton("Конвертировать");
        frameSelectParam = new FrameSelectParam();
        serviceFromFrameSelectParam = frameSelectParam.getServiceAbonentsAndParam();
        listChosenParamsFromFrameSelectParam = frameSelectParam.getListChosenParams();
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

                            //1. Создание маркеров начала цикла в файле
                            List<Integer> markerCycleAtFile = new ArrayList<>();
                            long before = System.currentTimeMillis();
                            Set<Abonent> abonentSet = serviceFromFrameSelectParam.getListAllAbonents();
                            List<Abonent> abonentList = new ArrayList<>();
                            abonentList.addAll(abonentSet);
                            byte adrFirstAbonent = abonentList.get(0).getAdr();
                            byte komFirstAbonent = abonentList.get(0).getKom();
                            short byteZPRFirstAbonent = abonentList.get(0).getByteZPR();
                            for (int index = 0; index < buffer.length - 1; index++) {
                                if (buffer[index] == adrFirstAbonent && buffer[index + 1] == komFirstAbonent
                                        && index + byteZPRFirstAbonent < buffer.length)

                                {
                                    //1. Выделяем память под обмен БВК: 0х01, 0х01
                                    byte[] bufferAbonent = new byte[byteZPRFirstAbonent];
                                    int lenBuff = bufferAbonent.length;
                                    bufferAbonent = Arrays.copyOfRange(buffer, index, index + byteZPRFirstAbonent);
                                    //2. Считаем CRC
                                    CRC16 crc16 = new CRC16();
                                    crc16.calc(Arrays.copyOfRange(bufferAbonent, 0, bufferAbonent.length - 2));
                                    //3. Сверяем CRC: расчетный и из файла
                                    if ((crc16.getHi() == bufferAbonent[lenBuff -2]) && (crc16.getLow() == bufferAbonent[lenBuff - 1])) {
                                        //4. Добавляем в список начало нового цикла
                                        markerCycleAtFile.add(index);
                                        fileOutput.write(Integer.toString(index).getBytes());
                                        fileOutput.write(delimiter.getBytes());
                                        //fileOutput.write(System.lineSeparator().getBytes());
                                    }
                                }
                            }

                            //2. Расшифровка выбранных параметров
                            int byteAtOneCycle = 0;
                            if (markerCycleAtFile.size() > 0) {
                                byteAtOneCycle = markerCycleAtFile.get(1) - markerCycleAtFile.get(0);
                            }
                            if (byteAtOneCycle > 0) {
                                //for (int index = 0; index < buffer.length - 1; index++) {
                                for (int index = 0; index < 757; index++) {
                                        for (int indParam = 0; indParam < listChosenParamsFromFrameSelectParam.size(); indParam++) {
                                            if (buffer[index] == listChosenParamsFromFrameSelectParam.get(indParam).getAdr()
                                                    && buffer[index + 1] == listChosenParamsFromFrameSelectParam.get(indParam).getKom())
                                            {
                                                byte[] bufferAbonent;
                                                //1. Свойства абонента:
                                                Abonent abonent = serviceFromFrameSelectParam.findByObmen(listChosenParamsFromFrameSelectParam.get(indParam).getNameObm());
                                                byte adr = abonent.getAdr();           //адрес абонента
                                                byte kom = abonent.getKom();           //команда
                                                short byteZPR = abonent.getByteZPR();  //кол-во байт запроса к абоненту
                                                short byteOTV = abonent.getByteOTV();  //кол-во байт в ответе
                                                String nameObm = abonent.getNameObm(); //название абонента
                                                //2. Свойства параметра:
                                                Param param = listChosenParamsFromFrameSelectParam.get(indParam);
                                                //byte adr = param.getAdr();           //адрес абонента
                                                //byte kom = param.getKom();           //команда
                                                short byteParam = listChosenParamsFromFrameSelectParam.get(indParam).getByteParam();    //позиция байта в посылке
                                                String typeObm = listChosenParamsFromFrameSelectParam.get(indParam).getTypeObm();       //тип обмена:
                                                                                                                                        //(REQ) - запрос, (ANS) - ответ
                                                                                                                                        //String nameObm;     //название абонента
                                                String nameParam = listChosenParamsFromFrameSelectParam.get(indParam).getNameParam();   //название параметра полное
                                                String nameSmall = listChosenParamsFromFrameSelectParam.get(indParam).getNameSmall();   //название параметра(сокращенное)
                                                String typeParam = listChosenParamsFromFrameSelectParam.get(indParam).getTypeParam();   //тип параметра: ANLG1, ANLG2
                                                byte sign = listChosenParamsFromFrameSelectParam.get(indParam).getSign();               //знак параметра:
                                                                                                                                        //0 - без знака, 1 - со знаком
                                                float cmr = listChosenParamsFromFrameSelectParam.get(indParam).getCmr();                //ЦМР параметра
                                                float hight = listChosenParamsFromFrameSelectParam.get(indParam).getHight();            //какая часть идет первая?
                                                                                                                                        //0 - младшая,   1 - старшая
                                                float countByte = listChosenParamsFromFrameSelectParam.get(indParam).getCountByte();    // кол-во байт у параметра
                                                //3. Выделяем память под обмен
                                                if (listChosenParamsFromFrameSelectParam.get(indParam).getTypeObm() == "REQ") {
                                                    bufferAbonent = new byte[abonent.getByteZPR()];
                                                } else {
                                                    bufferAbonent = new byte[abonent.getByteOTV()];
                                                }


                                                int lenBuff = bufferAbonent.length;
                                                bufferAbonent = Arrays.copyOfRange(buffer, index, index + bufferAbonent.length);
                                                //2. Считаем CRC (для параметра)
                                                CRC16 crc16 = new CRC16();
                                                crc16.calc(Arrays.copyOfRange(bufferAbonent, 0, bufferAbonent.length - 2));
                                                //3. Сверяем CRC: расчетный и из файла
                                                if ((crc16.getHi() == bufferAbonent[lenBuff -2]) && (crc16.getLow() == bufferAbonent[lenBuff - 1])) {
                                                    //4. Добавляем в файл значение
                                                    markerCycleAtFile.add(index);
                                                    fileOutput.write(Integer.toString(index).getBytes());
                                                    fileOutput.write(delimiter.getBytes());
                                                    //fileOutput.write(System.lineSeparator().getBytes());
                                                }
                                            }
                                        }
                                        if (index > 0 && index % 754 == 0) {
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
