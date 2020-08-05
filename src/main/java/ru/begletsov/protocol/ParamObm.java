package ru.begletsov.protocol;

/**
 * Класс-данных ParamObm содержит параметры(байты данных), которые принимаются/передаются от/к абоненту
 * 1) создание класса
 * @author Sergei Begletsov
 * @since 05.08.2020
 * @version 1
 */

public class ParamObm {
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

    public ParamObm(byte adr, byte kom, short byteParam, String typeObm, String nameObm, String nameParam, String nameSmall, String typeParam, byte sign, float cmr, float hight, float countByte) {
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
}
