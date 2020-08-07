package ru.begletsov.frame;

import ru.begletsov.chose_param.ListChoseParam;
import ru.begletsov.component.CheckBoxList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.*;
import java.util.List;

/**
 * Класс FrameSelectParam - окно выбора параметров
 * 1) создание класса
 * @author Sergei Begletsov
 * @since 07.08.2020
 * @version 1
 */

public class FrameSelectParam extends JFrame {
    private JTabbedPane tabbedPane;
    private CheckBoxList cbList;
    private List<JCheckBox> listCheckBox;
    private ListChoseParam listChoseParam;

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
}
