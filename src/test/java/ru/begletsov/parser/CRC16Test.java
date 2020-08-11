package ru.begletsov.parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class CRC16Test {

    @Test
    public void whenCalcCRC16For5ByteData() {
        //1. Входные данные
        CRC16 crc16 = new CRC16();
        byte[] data = new byte[] {0x01, 0x02, 0x33, 0x44, 0x55};
        //2. Расчет контрольной суммы,
        //   и получение значения
        for (int d : data) {
            crc16.update(d);
        }
        //3. Ожидаемые значения
        byte expHi = (byte) 0x48;
        byte expLow = (byte) 0x1B;
        //4. Проверка
        assertEquals(expHi, crc16.getHi());
        assertEquals(expLow, crc16.getLow());
    }

    @Test
    public void whenCalcCRC16For6ByteData() {
        //1. Входные данные
        CRC16 crc16 = new CRC16();
        byte[] data = new byte[] {0x01, 0x02, 0x33, 0x44, 0x55, 0x66};
        //2. Расчет контрольной суммы
        crc16.calc(data);
        byte[] crcBytes = crc16.getCrcBytes();
        //3. Ожидаемые значения
        byte[] expByte = new byte[] {(byte) 0x21, (byte) 0x88};
        //4. Проверка
        assertArrayEquals(expByte, crcBytes);
    }

    @Test
    public void whenGetValueFor2ByteData() {
        //1. Входные данные
        CRC16 crc16 = new CRC16();
        byte[] data = new byte[] {0x01, 0x02};
        //2. Расчет контрольной суммы
        crc16.calc(data);
        long crcLongCalc = crc16.getValue();
        //3. Ожидаемые значения
        long expLong = 0xE181;
        //4. Проверка
        assertEquals(expLong, crcLongCalc);
    }

    @Test
    public void whenResetCRC16() {
        //1. Входные данные
        CRC16 crc16 = new CRC16();
        byte[] data = new byte[] {0x01, 0x02, 0x33, 0x44, 0x55, 0x66};
        //2. Расчет контрольной суммы
        crc16.calc(data);
        //3. Сброс CRC
        crc16.reset();
        //4. Ожидаемые значения
        byte expHi = (byte) 0xFF;
        byte expLow = (byte) 0xFF;
        //5. Проверка
        assertEquals(expHi, crc16.getHi());
        assertEquals(expLow, crc16.getLow());
    }

    @Test
    public void whenUpdateByteDataAndAdd1Byte() {
        //1. Входные данные
        CRC16 crc16 = new CRC16();
        byte[] data = new byte[] {0x01, 0x02, 0x33, 0x44, 0x55};
        //2. Расчет контрольной суммы
        crc16.calc(data);
        //3. Обновление массива и контрольной суммы
        crc16.update((byte) 0x66);
        //4. Ожидаемые значения
        byte expHi = (byte) 0x21;
        byte expLow = (byte) 0x88;
        //5. Проверка
        assertEquals(expHi, crc16.getHi());
        assertEquals(expLow, crc16.getLow());
    }

    @Test
    public void whenUpdateByteDataAndAdd3Byte() {
        //1. Входные данные
        CRC16 crc16 = new CRC16();
        byte[] data = new byte[] {0x01, 0x02, 0x33, 0x44, 0x55};
        byte[] newByteData = new byte[] {0x01, 0x02, 0x33, 0x44, 0x55, 0x66, 0x77, 0x12};
        //2. Расчет контрольной суммы
        crc16.calc(data);
        //3. Обновление массива + контрольной суммы,
        //   получение значения
        crc16.update(newByteData, 5, 3);
        //4. Ожидаемые значения
        byte expHi = (byte) 0xE5;
        byte expLow = (byte) 0x01;
        //5. Проверка
        assertEquals(expHi, crc16.getHi());
        assertEquals(expLow, crc16.getLow());
    }
}