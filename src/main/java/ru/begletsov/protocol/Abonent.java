package ru.begletsov.protocol;

import java.util.Objects;

/**
 * Класс-данных Abonent содержит протокол обмена с абонентами для заказа УГЭ-300
 * 1) создание класса
 * @author Sergei Begletsov
 * @since 05.08.2020
 * @version 1
 */

public class Abonent {
    private byte adr;       //адрес абонента
    private byte kom;       //команда
    private short byteZPR;  //кол-во байт запроса к абоненту
    private short byteOTV;  //кол-во байт в ответе
    private String nameObm; //название абонента

    public Abonent(byte adr, byte kom, short byteZPR, short byteOTV, String nameObm) {
        this.adr = adr;
        this.kom = kom;
        this.byteZPR = byteZPR;
        this.byteOTV = byteOTV;
        this.nameObm = nameObm;
    }

    public byte getAdr() {
        return adr;
    }

    public byte getKom() {
        return kom;
    }

    public short getByteZPR() {
        return byteZPR;
    }

    public short getByteOTV() {
        return byteOTV;
    }

    public String getNameObm() {
        return nameObm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Abonent abonent = (Abonent) o;
        return adr == abonent.adr &&
                kom == abonent.kom &&
                byteZPR == abonent.byteZPR &&
                byteOTV == abonent.byteOTV &&
                Objects.equals(nameObm, abonent.nameObm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adr, kom, byteZPR, byteOTV, nameObm);
    }
}
