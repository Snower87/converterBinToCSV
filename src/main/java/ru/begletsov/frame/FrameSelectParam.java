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
import java.util.stream.Collectors;

/**
 * Класс FrameSelectParam - окно выбора параметров
 * 1) создание класса 2) добавил объект класса Service со списком абонентов + методы добавляющие абоненты и их пар-ры
 * 3) добавил списки listCheckBox для каждого абонента: BVKsh, BPS_UVKE_SM, KI, J1939
 * @author Sergei Begletsov
 * @since 07.08.2020
 * @version 2
 */

public class FrameSelectParam extends JFrame {
    private JTabbedPane tabbedPane;
    private List<JCheckBox> listCheckBox;
    private List<JCheckBox> listCheckBox_BVKsh;
    private List<JCheckBox> listCheckBox_BPS_UVKE_SM;
    private List<JCheckBox> listCheckBox_KI;
    private List<JCheckBox> listCheckBox_J1939;
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

        listCheckBox = new ArrayList<>();
        listCheckBox_BVKsh = new ArrayList<>();
        listCheckBox_BPS_UVKE_SM = new ArrayList<>();
        listCheckBox_KI = new ArrayList<>();
        listCheckBox_J1939 = new ArrayList<>();
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

        //listCheckBox = (Vector) Arrays.asList(myList);
        listCheckBox = Arrays.asList(myList);
        //cbList.setListData((Vector) listCheckBox);

        //Добавление абонентов и их пар-ов на панель выбора
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        Set<Abonent> abonentSet = serviceAbonentsAndParam.getListAllAbonents();
        for (Abonent abonent: abonentSet) {
            String nameObmen = abonent.getNameObm();
            //если у абонента есть параметры, то добавляю его на панель tabbedPane
            if (serviceAbonentsAndParam.getListParamForAbonent(nameObmen).size() != 0) {
                //получаю список пар-ров обмена для абонента
                List<Param> listParamForAbonent = serviceAbonentsAndParam.getListParamForAbonent(nameObmen);
                int countParam = serviceAbonentsAndParam.getListParamForAbonent(nameObmen).size();
                JCheckBox[] massCheckBoxes = new JCheckBox[countParam];
                for (int t = 0; t < countParam; t++) {
                    massCheckBoxes[t] = new JCheckBox(listParamForAbonent.get(t).getNameSmall());
                }
                CheckBoxList checkBoxListCurrAbonent = new CheckBoxList();
                checkBoxListCurrAbonent.setListData(massCheckBoxes);

                switch (nameObmen) {
                    case "BR_BVKsh":
                    case "BR_KI":
                    case "BR_J1939":
                    case "BR_BPS_UVKE_SM":
                        listCheckBox_BVKsh = Arrays.asList(massCheckBoxes);
                        break;
                }
                tabbedPane.addTab(nameObmen, checkBoxListCurrAbonent);
            }
        }
        add(tabbedPane);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                listChoseParam.setListChoseParam_UVKE_SM(getChoseUserParam(listCheckBox));

                listChoseParam.setListChoseParam_BVKsh(getChoseUserParam(listCheckBox_BVKsh));
                listChoseParam.setListChoseParam_UVKE_SM(getChoseUserParam(listCheckBox_BPS_UVKE_SM));
                listChoseParam.setListChoseParam_KI(getChoseUserParam(listCheckBox_KI));
                listChoseParam.setListChoseParam_J1939(getChoseUserParam(listCheckBox_J1939));
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
                //rsl.add(ind);
                rsl.add(listCheckBox.get(ind).getText());
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
