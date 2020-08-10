package ru.begletsov.chose_param;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Класс-данных ListCheckBoxAbonent содержит все CheckBox для каждого абонента
 * 1) создание класса
 * @author Sergei Begletsov
 * @since 10.08.2020
 * @version 1
 */

public class ListCheckBoxAbonent {
    private List<JCheckBox> listCheckBox_BVKsh;
    private List<JCheckBox> listCheckBox_BPS_UVKE_SM;
    private List<JCheckBox> listCheckBox_KI;
    private List<JCheckBox> listCheckBox_J1939;

    public ListCheckBoxAbonent() {
        listCheckBox_BVKsh = new ArrayList<>();
        listCheckBox_BPS_UVKE_SM = new ArrayList<>();
        listCheckBox_KI = new ArrayList<>();
        listCheckBox_J1939 = new ArrayList<>();
    }

    /**
     * Получение списка listCheckBox по названию абонента (геттер)
     * @param nameAbonent название абонента
     * @return список CheckBox для абонента при успешном поиске, null - если абонента не нашли
     */
    public List<JCheckBox> getListCheckBoxByNameAbonent(String nameAbonent) {
        List<JCheckBox> rsl = new ArrayList<>();
        switch (nameAbonent) {
            case "BR_BVKsh":
                rsl = listCheckBox_BVKsh;
                break;
            case "BR_KI":
                rsl = listCheckBox_KI;
                break;
            case "BR_J1939":
                rsl = listCheckBox_J1939;
                break;
            case "BR_BPS_UVKE_SM":
                rsl = listCheckBox_BPS_UVKE_SM;
                break;
        }
        return rsl;
    }

    /**
     * Задание списка listCheckBox по названию абонента (сеттер)
     * @param nameAbonent название абонента
     * @param listCheckBox входной список CheckBox, который надо задать
     * @return true - если список для абонента задан успешно, false - список не задан
     */
    public boolean setListCheckBoxByNameAbonent(String nameAbonent, List<JCheckBox> listCheckBox) {
        boolean rsl = false;
        switch (nameAbonent) {
            case "BR_BVKsh":
                listCheckBox_BVKsh = listCheckBox;
                rsl = true;
                break;
            case "BR_KI":
                listCheckBox_KI = listCheckBox;
                rsl = true;
                break;
            case "BR_J1939":
                listCheckBox_J1939 = listCheckBox;
                rsl = true;
                break;
            case "BR_BPS_UVKE_SM":
                listCheckBox_BPS_UVKE_SM =  listCheckBox;
                rsl = true;
                break;
        }
        return rsl;
    }
}