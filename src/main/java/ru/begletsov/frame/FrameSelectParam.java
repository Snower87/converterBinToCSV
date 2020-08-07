package ru.begletsov.frame;

import ru.begletsov.chose_param.ListChoseParam;
import ru.begletsov.component.CheckBoxList;
import ru.begletsov.protocol.Abonent;
import ru.begletsov.protocol.Param;
import ru.begletsov.protocol.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
import java.util.List;

/**
 * Класс FrameSelectParam - окно выбора параметров
 * 1) создание класса 2) добавил объект класса Service со списком абонентов + методы добавляющие абоненты и их пар-ры
 * @author Sergei Begletsov
 * @since 07.08.2020
 * @version 2
 */

public class FrameSelectParam extends JFrame {
    private JTabbedPane tabbedPane;
    private CheckBoxList cbList;
    private List<JCheckBox> listCheckBox;
    private ListChoseParam listChoseParam;
    private Service serviceAbonentsAndParam;

    public FrameSelectParam() throws HeadlessException {
        setVisible(false);

        //take screen size
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        //set height and width frame
        //setSize(screenWidth/2, screenHeight/2);
        setSize(400, 400);
        setLocationByPlatform(true);

        cbList = new CheckBoxList();
        listCheckBox = new ArrayList<>();
        listChoseParam = new ListChoseParam();
        serviceAbonentsAndParam = new Service();
        this.addAbonentsRS485();
        this.addParamRS485();
        JCheckBox check1 = new JCheckBox("One");
        JCheckBox check2 = new JCheckBox("Two");
        JCheckBox check3 = new JCheckBox("3");
        JCheckBox check4 = new JCheckBox("4");
        JCheckBox check5 = new JCheckBox("5");
        JCheckBox check6 = new JCheckBox("6");
        JCheckBox check7 = new JCheckBox("7");
        JCheckBox check8 = new JCheckBox("8");
        JCheckBox check9 = new JCheckBox("9");
        JCheckBox check10 = new JCheckBox("10");
        JCheckBox[] myList = { check1, check2, check3, check4, check5, check6, check7, check8, check9, check10};
        //JList[] myList = { check1, check2, check3, check4, check5, check6};
        cbList.setListData(myList);

        //listCheckBox = (Vector) Arrays.asList(myList);
        listCheckBox = Arrays.asList(myList);
        //cbList.setListData((Vector) listCheckBox);

        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        tabbedPane.addTab("BR_BVKsh", new JLabel("Label BR_BVKsh"));
        tabbedPane.addTab("BR_BPS_UVKE_SM",cbList);
        tabbedPane.addTab("BR_BR_KI", new JLabel("Label BR_BR_KI"));
        tabbedPane.addTab("BR_ZPR_BR1", new JLabel("Label BR_ZPR_BR1"));
        tabbedPane.addTab("BR_ZPR_BR2", new JLabel("Label BR_ZPR_BR2"));
        tabbedPane.addTab("BR_ZPR_BR3", new JLabel("Label BR_ZPR_BR3"));
        tabbedPane.addTab("BR_J1939", new JLabel("Label BR_J1939"));
        tabbedPane.addTab("BR_JN2100", new JLabel("Label BR_JN2100"));
        tabbedPane.addTab("BR_TSR_CAN", new JLabel("Label BR_TSR_CAN"));
        tabbedPane.addTab("BR_P10x", new JLabel("Label BR_P10x"));
        tabbedPane.addTab("BR_DIAGN", new JLabel("Label BR_DIAGN"));
        tabbedPane.addTab("BR_CORR1", new JLabel("Label BR_CORR1"));

        add(tabbedPane);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                listChoseParam.setListChoseParam_UVKE_SM(getChoseUserParam(listCheckBox));
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }

    public List getChoseUserParam(List<JCheckBox> listCheckBox) {
        List rsl = new ArrayList();
        for (int ind = 0; ind < listCheckBox.size(); ind++) {
            if (listCheckBox.get(ind).isSelected()) {
                rsl.add(ind);
            }
        }
        return rsl;
    }

    public void addAbonentsRS485() {
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x01, (byte)0x02, (short) 64, (short) 0,"BR_BVKsh"));
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x01, (byte)0x03, (short) 64, (short) 0,"BR_BPS_UVKE_SM"));
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x01, (byte)0x04, (short) 52, (short) 0,"BR_KI"));
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x01, (byte)0x05, (short) 44, (short) 0,"BR_ZPR_BR1"));
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x01, (byte)0x06, (short) 44, (short) 0,"BR_ZPR_BR2"));
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x01, (byte)0x07, (short) 44, (short) 0,"BR_ZPR_BR3"));
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x02, (byte)0x01, (short) 59, (short) 0,"BR_J1939"));
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x04, (byte)0x01, (short) 88, (short) 0,"BR_JN2100"));
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x04, (byte)0x02, (short) 68, (short) 0,"BR_TSR_CAN"));
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x04, (byte)0x03, (short) 72, (short) 0,"BR_P10x"));
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x06, (byte)0x01, (short) 70, (short) 0,"BR_DIAGN"));
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x06, (byte)0x02, (short) 33, (short) 0,"BR_CORR1"));
    }

    public void addParamRS485() {
        serviceAbonentsAndParam.addParam("BR_BVKsh", new Param((byte)0x01, (byte)0x02, (short) 3, "REQ", "BR_BVKsh", "Управление величиной открытия клапана YP1", "YP1", "ANLG2", (byte)0, 1, 1, 1));
        serviceAbonentsAndParam.addParam("BR_BVKsh", new Param((byte)0x01, (byte)0x02, (short) 4, "REQ", "BR_BVKsh", "Управление величиной открытия клапана YP2", "YP2", "ANLG2", (byte)0, 1, 1, 1));
        serviceAbonentsAndParam.addParam("BR_BVKsh", new Param((byte)0x01, (byte)0x02, (short) 5, "REQ", "BR_BVKsh", "Управление величиной открытия клапана YP3", "YP3", "ANLG2", (byte)0, 1, 1, 1));

        serviceAbonentsAndParam.addParam("BR_BPS_UVKE_SM", new Param((byte)0x01, (byte)0x03, (short) 47, "REQ", "BR_BPS_UVKE_SM", "Температура в кабине машиниста", "Temp_cabin_SMK", "ANLG2", (byte)0, 1, 1, 1));

        serviceAbonentsAndParam.addParam("BR_KI", new Param((byte)0x01, (byte)0x04, (short) 41, "REQ", "BR_KI", "Давление ГМ в пневмогидроаккумуляторе АК3", "DI7.2_KI3", "ANLG1", (byte)0, 0.01f, 0, 2));

        serviceAbonentsAndParam.addParam("BR_J1939", new Param((byte)0x02, (byte)0x01, (short) 3, "REQ", "BR_J1939", "Уставка по частоте вращения дизеля", "n_Diezel_ust", "ANLG1", (byte)0, 0.125f, 0, 2));
        serviceAbonentsAndParam.addParam("BR_J1939", new Param((byte)0x02, (byte)0x01, (short) 9, "REQ", "BR_J1939", "Текущая скорость", "Engine_Speed", "ANLG1", (byte)0, 0.125f, 0, 2));
    }
}
