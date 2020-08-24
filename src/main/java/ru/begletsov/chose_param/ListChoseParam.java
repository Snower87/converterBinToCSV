package ru.begletsov.chose_param;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-данных ListChoseParam содержит все выбранные параметры из окна FrameSelectParam
 * 1) создание класса 2) добавил список всех выбранных пользователем параметров listChosenParam (а также методы get-set)
 * @author Sergei Begletsov
 * @since 07.08.2020
 * @version 2
 */

public class ListChoseParam {
    private List listChoseParam_BVKsh;
    private List listChoseParam_UVKE_SM;
    private List listChoseParam_KI;
    private List listChoseParam_BR1;
    private List listChoseParam_BR2;
    private List listChoseParam_BR3;
    private List listChoseParam_J1939;
    private List listChoseParam_JN2100;
    private List listChoseParam_TSR_CAN;
    private List listChoseParam_P10x;
    private List listChoseParam_DIAGN;
    private List listChoseParam_CORR1;
    private List listChosenParam;

    public ListChoseParam() {
        listChoseParam_BVKsh = new ArrayList();
        listChoseParam_UVKE_SM = new ArrayList();
        listChoseParam_KI = new ArrayList();
        listChoseParam_BR1 = new ArrayList();
        listChoseParam_BR2 = new ArrayList();
        listChoseParam_BR3 = new ArrayList();
        listChoseParam_J1939 = new ArrayList();
        listChoseParam_JN2100 = new ArrayList();
        listChoseParam_TSR_CAN = new ArrayList();
        listChoseParam_P10x = new ArrayList();
        listChoseParam_DIAGN = new ArrayList();
        listChoseParam_CORR1 = new ArrayList();
        listChosenParam = new ArrayList();
    }

    public List getListChoseParam_BVKsh() {
        return listChoseParam_BVKsh;
    }

    public List getListChoseParam_UVKE_SM() {
        return listChoseParam_UVKE_SM;
    }

    public List getListChoseParam_KI() {
        return listChoseParam_KI;
    }

    public List getListChoseParam_BR1() {
        return listChoseParam_BR1;
    }

    public List getListChoseParam_BR2() {
        return listChoseParam_BR2;
    }

    public List getListChoseParam_BR3() {
        return listChoseParam_BR3;
    }

    public List getListChoseParam_J1939() {
        return listChoseParam_J1939;
    }

    public List getListChoseParam_JN2100() {
        return listChoseParam_JN2100;
    }

    public List getListChoseParam_TSR_CAN() {
        return listChoseParam_TSR_CAN;
    }

    public List getListChoseParam_P10x() {
        return listChoseParam_P10x;
    }

    public List getListChoseParam_DIAGN() {
        return listChoseParam_DIAGN;
    }

    public List getListChoseParam_CORR1() {
        return listChoseParam_CORR1;
    }

    public List getListChosenParam() {
        return listChosenParam;
    }

    public void setListChoseParam_BVKsh(List listChoseParam_BVKsh) {
        this.listChoseParam_BVKsh = listChoseParam_BVKsh;
    }

    public void setListChoseParam_UVKE_SM(List listChoseParam_UVKE_SM) {
        this.listChoseParam_UVKE_SM = listChoseParam_UVKE_SM;
    }

    public void setListChoseParam_KI(List listChoseParam_KI) {
        this.listChoseParam_KI = listChoseParam_KI;
    }

    public void setListChoseParam_BR1(List listChoseParam_BR1) {
        this.listChoseParam_BR1 = listChoseParam_BR1;
    }

    public void setListChoseParam_BR2(List listChoseParam_BR2) {
        this.listChoseParam_BR2 = listChoseParam_BR2;
    }

    public void setListChoseParam_BR3(List listChoseParam_BR3) {
        this.listChoseParam_BR3 = listChoseParam_BR3;
    }

    public void setListChoseParam_J1939(List listChoseParam_J1939) {
        this.listChoseParam_J1939 = listChoseParam_J1939;
    }

    public void setListChoseParam_JN2100(List listChoseParam_JN2100) {
        this.listChoseParam_JN2100 = listChoseParam_JN2100;
    }

    public void setListChoseParam_TSR_CAN(List listChoseParam_TSR_CAN) {
        this.listChoseParam_TSR_CAN = listChoseParam_TSR_CAN;
    }

    public void setListChoseParam_P10x(List listChoseParam_P10x) {
        this.listChoseParam_P10x = listChoseParam_P10x;
    }

    public void setListChoseParam_DIAGN(List listChoseParam_DIAGN) {
        this.listChoseParam_DIAGN = listChoseParam_DIAGN;
    }

    public void setListChoseParam_CORR1(List listChoseParam_CORR1) {
        this.listChoseParam_CORR1 = listChoseParam_CORR1;
    }

    public void setListChosenParam(List listChosenParam) {
        for (Object obj: listChosenParam) {
            this.listChosenParam.add(obj);
        }
    }

    public int getIndexChosenParam(String nameParam) {
        return this.listChosenParam.indexOf(nameParam);
    }
}
