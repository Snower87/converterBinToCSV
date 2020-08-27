package ru.begletsov.frame;

import ru.begletsov.chose_param.ListCheckBoxAbonent;
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
 * 3) добавил списки listCheckBox для каждого абонента: BVKsh, BPS_UVKE_SM, KI, J1939 4) чистка проекта, добавил новые параметры для абонентов
 * 5) добавил список listCheckBoxAbonent, который содержит в себе все списки для абонентов: listCheckBox_BVKsh, BPS_UVKE_SM, KI, J1939 и т.д.
 * 6) добавил список выбранных пользователем параметров listChosenParams (+ геттер) 7) добавил нового абонента 0x01 0x01 (BR_BVK)
 * @author Sergei Begletsov
 * @since 07.08.2020
 * @version 7
 */

public class FrameSelectParam extends JFrame {
    private JTabbedPane tabbedPane;
    private ListCheckBoxAbonent listCheckBoxAbonent;
    private ListChoseParam listChoseParam;
    private Service serviceAbonentsAndParam;
    private List<Param> listChosenParams;

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

        listCheckBoxAbonent = new ListCheckBoxAbonent();
        listChoseParam = new ListChoseParam();
        serviceAbonentsAndParam = new Service();
        listChosenParams = new ArrayList<>();

        this.addAbonentsRS485();
        this.addParamRS485();

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

                listCheckBoxAbonent.setListCheckBoxByNameAbonent(nameObmen, Arrays.asList(massCheckBoxes));
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
                listChoseParam.setListChoseParam_BVKsh(getChoseUserParam(listCheckBoxAbonent.getListCheckBoxByNameAbonent("BR_BVKsh")));
                listChoseParam.setListChoseParam_UVKE_SM(getChoseUserParam(listCheckBoxAbonent.getListCheckBoxByNameAbonent("BR_BPS_UVKE_SM")));
                listChoseParam.setListChoseParam_KI(getChoseUserParam(listCheckBoxAbonent.getListCheckBoxByNameAbonent("BR_KI")));
                listChoseParam.setListChoseParam_J1939(getChoseUserParam(listCheckBoxAbonent.getListCheckBoxByNameAbonent("BR_J1939")));

                listChoseParam.setListChosenParam(getChoseUserParam(listCheckBoxAbonent.getListCheckBoxByNameAbonent("BR_BVKsh")));
                listChoseParam.setListChosenParam(getChoseUserParam(listCheckBoxAbonent.getListCheckBoxByNameAbonent("BR_BPS_UVKE_SM")));
                listChoseParam.setListChosenParam(getChoseUserParam(listCheckBoxAbonent.getListCheckBoxByNameAbonent("BR_KI")));
                listChoseParam.setListChosenParam(getChoseUserParam(listCheckBoxAbonent.getListCheckBoxByNameAbonent("BR_J1939")));
                getListChosenParams();
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
            boolean asdas  = listCheckBox.get(ind).isSelected();
            if (listCheckBox.get(ind).isSelected()) {
                //rsl.add(ind);
                rsl.add(listCheckBox.get(ind).getText());
            }
        }
        return rsl;
    }

    public void addAbonentsRS485() {
        serviceAbonentsAndParam.addObmen(new Abonent((byte)0x01, (byte)0x01, (short) 55, (short) 0,"BR_BVK"));
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
        serviceAbonentsAndParam.addParam("BR_BVKsh", new Param((byte)0x01, (byte)0x02, (short) 6, "REQ", "BR_BVKsh", "Управление величиной открытия клапана YP4", "YP4", "ANLG2", (byte)0, 1, 1, 1));
        serviceAbonentsAndParam.addParam("BR_BVKsh", new Param((byte)0x01, (byte)0x02, (short) 7, "REQ", "BR_BVKsh", "Управление величиной открытия клапана YP5", "YP5", "ANLG2", (byte)0, 1, 1, 1));
        serviceAbonentsAndParam.addParam("BR_BVKsh", new Param((byte)0x01, (byte)0x02, (short) 8, "REQ", "BR_BVKsh", "Управление величиной открытия клапана YP6", "YP6", "ANLG2", (byte)0, 1, 1, 1));
        serviceAbonentsAndParam.addParam("BR_BVKsh", new Param((byte)0x01, (byte)0x02, (short) 9, "REQ", "BR_BVKsh", "Управление величиной открытия клапана YP7", "YP7", "ANLG2", (byte)0, 1, 1, 1));

        serviceAbonentsAndParam.addParam("BR_BPS_UVKE_SM", new Param((byte)0x01, (byte)0x03, (short) 47, "REQ", "BR_BPS_UVKE_SM", "Температура в кабине машиниста", "Temp_cabin_SMK", "ANLG2", (byte)0, 1, 1, 1));
        serviceAbonentsAndParam.addParam("BR_BPS_UVKE_SM", new Param((byte)0x01, (byte)0x03, (short) 24, "REQ", "BR_BPS_UVKE_SM", "Скорость платформы от датчика 1.1", "speed_PP1", "ANLG1", (byte)1, 0.01f, 0, 2));
        serviceAbonentsAndParam.addParam("BR_BPS_UVKE_SM", new Param((byte)0x01, (byte)0x03, (short) 26, "REQ", "BR_BPS_UVKE_SM", "Скорость платформы от датчика 1.2", "speed_PP2", "ANLG1", (byte)1, 0.01f, 0, 2));
        serviceAbonentsAndParam.addParam("BR_BPS_UVKE_SM", new Param((byte)0x01, (byte)0x03, (short) 28, "REQ", "BR_BPS_UVKE_SM", "Угол поворота платформы от датчика 1.1", "angle_PP1", "ANLG1", (byte)0, 0.1f, 0, 2));
        serviceAbonentsAndParam.addParam("BR_BPS_UVKE_SM", new Param((byte)0x01, (byte)0x03, (short) 30, "REQ", "BR_BPS_UVKE_SM", "Угол поворота платформы от датчика 1.2", "angle_PP2", "ANLG1", (byte)0, 0.1f, 0, 2));
        serviceAbonentsAndParam.addParam("BR_BPS_UVKE_SM", new Param((byte)0x01, (byte)0x03, (short) 50, "REQ", "BR_BPS_UVKE_SM", "Неисправность 1", "fault_1_SMK_hex", "ANLG1", (byte)0, 1, 0, 2));

        serviceAbonentsAndParam.addParam("BR_KI", new Param((byte)0x01, (byte)0x04, (short) 41, "REQ", "BR_KI", "Давление ГМ в пневмогидроаккумуляторе АК3", "DI7.2_KI3", "ANLG1", (byte)0, 0.01f, 0, 2));
        serviceAbonentsAndParam.addParam("BR_KI", new Param((byte)0x01, (byte)0x04, (short) 3, "REQ", "BR_KI", "Напряжение бортовой сети", "U_bs_KI1", "ANLG1", (byte)0, 0.5f, 0, 2));
        serviceAbonentsAndParam.addParam("BR_KI", new Param((byte)0x01, (byte)0x04, (short) 5, "REQ", "BR_KI", "Изменение физических свойств гидравлического масла", "Ch_fiz_oil_KI1", "ANLG1", (byte)0, 0.01f, 0, 2));

        serviceAbonentsAndParam.addParam("BR_J1939", new Param((byte)0x02, (byte)0x01, (short) 3, "REQ", "BR_J1939", "Уставка по частоте вращения дизеля", "n_Diezel_ust", "ANLG1", (byte)0, 0.125f, 0, 2));
        serviceAbonentsAndParam.addParam("BR_J1939", new Param((byte)0x02, (byte)0x01, (short) 5, "REQ", "BR_J1939", "Уставка по моменту дизеля", "M_Diezel_ust", "ANLG2", (byte)0, 1, 1, 1));
        serviceAbonentsAndParam.addParam("BR_J1939", new Param((byte)0x02, (byte)0x01, (short) 9, "REQ", "BR_J1939", "Текущая скорость", "Engine_Speed", "ANLG1", (byte)0, 0.125f, 0, 2));
        serviceAbonentsAndParam.addParam("BR_J1939", new Param((byte)0x02, (byte)0x01, (short) 54, "REQ", "BR_J1939", "Моточасы", "Моточасы", "ANLG1", (byte)0, 0.05f, 0, 4));
    }


    /**
     * Получить список выбранных пользователем параметров
     * @return список параметров
     */
    public List<Param> getListChosenParams() {
        Set<Abonent> abonentSet = serviceAbonentsAndParam.getListAllAbonents();
        for (Abonent abonent: abonentSet) {
            String nameObmen = abonent.getNameObm();
            //если у абонента есть параметры, то добавляю его на панель tabbedPane
            if (serviceAbonentsAndParam.getListParamForAbonent(nameObmen).size() != 0) {
                //получаю список пар-ров обмена для абонента
                List<Param> listParamForAbonent = serviceAbonentsAndParam.getListParamForAbonent(nameObmen);
                int countParam = serviceAbonentsAndParam.getListParamForAbonent(nameObmen).size();
                //получаю список выбранных пользователем параметров
                List userChosenParam = listChoseParam.getListChosenParam();
                JCheckBox[] massCheckBoxes = new JCheckBox[countParam];
                for (int t = 0; t < countParam; t++) {
                    String nameSmall = listParamForAbonent.get(t).getNameSmall();
                    if (listChoseParam.getIndexChosenParam(nameSmall) >= 0) {
                        listChosenParams.add(listParamForAbonent.get(t));
                    }
                }
            }
        }
        return listChosenParams;
    }

    /**
     * Получить данные обо всех абонентах и их параметрах
     * @return заполненный объект Service
     */
    public Service getServiceAbonentsAndParam() {
        return serviceAbonentsAndParam;
    }
}
