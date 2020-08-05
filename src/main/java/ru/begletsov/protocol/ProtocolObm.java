package ru.begletsov.protocol;

/**
 * Класс-данных ProtocolObm содержит протокол обмена с абонентами по УГЭ-300
 * 1) создание класса
 * @author Sergei Begletsov
 * @since 05.08.2020
 * @version 1
 */

public class ProtocolObm {
    private byte adr;       //адрес абонента
    private byte kom;       //команда
    private short byteZPR;  //кол-во байт запроса к абоненту
    private short byteOTV;  //кол-во байт в ответе
    private String nameObm; //название абонента

    public ProtocolObm(byte adr, byte kom, short byteZPR, short byteOTV, String nameObm) {
        this.adr = adr;
        this.kom = kom;
        this.byteZPR = byteZPR;
        this.byteOTV = byteOTV;
        this.nameObm = nameObm;
    }
}
