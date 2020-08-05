package ru.begletsov.protocol;

import java.util.Objects;

/**
 * Класс-данных Param содержит параметры (байт-данные), которые принимаются/передаются от/к абоненту
 * 1) создание класса
 * @author Sergei Begletsov
 * @since 05.08.2020
 * @version 1
 */


public class Param {
    private byte adr;           //адрес абонента
    private byte kom;           //команда
    private short byteParam;    //позиция байта в посылке
    private String typeObm;     //тип обмена:
                                //(REQ) - запрос, (ANS) - ответ
    private String nameObm;     //название абонента
    private String nameParam;   //название параметра полное
    private String nameSmall;   //название параметра(сокращенное)
    private String typeParam;   //тип параметра: ANLG1, ANLG2
    private byte sign;          //знак параметра:
                                //0 - без знака, 1 - со знаком
    private float cmr;          //ЦМР параметра
    private float hight;        //какая часть идет первая?
                                //0 - младшая,   1 - старшая
    private float countByte;    // кол-во байт у параметра

    public Param(byte adr, byte kom, short byteParam, String typeObm, String nameObm, String nameParam, String nameSmall, String typeParam, byte sign, float cmr, float hight, float countByte) {
        this.adr = adr;
        this.kom = kom;
        this.byteParam = byteParam;
        this.typeObm = typeObm;
        this.nameObm = nameObm;
        this.nameParam = nameParam;
        this.nameSmall = nameSmall;
        this.typeParam = typeParam;
        this.sign = sign;
        this.cmr = cmr;
        this.hight = hight;
        this.countByte = countByte;
    }

    public byte getAdr() {
        return adr;
    }

    public byte getKom() {
        return kom;
    }

    public short getByteParam() {
        return byteParam;
    }

    public String getTypeObm() {
        return typeObm;
    }

    public String getNameObm() {
        return nameObm;
    }

    public String getNameParam() {
        return nameParam;
    }

    public String getNameSmall() {
        return nameSmall;
    }

    public String getTypeParam() {
        return typeParam;
    }

    public byte getSign() {
        return sign;
    }

    public float getCmr() {
        return cmr;
    }

    public float getHight() {
        return hight;
    }

    public float getCountByte() {
        return countByte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Param param = (Param) o;
        return adr == param.adr &&
                kom == param.kom &&
                byteParam == param.byteParam &&
                sign == param.sign &&
                Float.compare(param.cmr, cmr) == 0 &&
                Float.compare(param.hight, hight) == 0 &&
                Float.compare(param.countByte, countByte) == 0 &&
                Objects.equals(typeObm, param.typeObm) &&
                Objects.equals(nameObm, param.nameObm) &&
                Objects.equals(nameParam, param.nameParam) &&
                Objects.equals(nameSmall, param.nameSmall) &&
                Objects.equals(typeParam, param.typeParam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adr, kom, byteParam, typeObm, nameObm, nameParam, nameSmall, typeParam, sign, cmr, hight, countByte);
    }
}
