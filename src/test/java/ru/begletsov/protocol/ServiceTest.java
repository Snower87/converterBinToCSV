package ru.begletsov.protocol;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ServiceTest {

    @Test
    public void whenAdd1Obmen() {
        Abonent bvkSh = new Abonent((byte) 0x01, (byte) 0x02, (byte) 64, (byte) 0, "BR_BVKsh");
        Service protocol = new Service();
        protocol.addObmen(bvkSh);
        assertThat(protocol.findByObmen(bvkSh.getNameObm()),is(bvkSh));
    }

    @Test
    public void whenAdd3Obmen() {
        Abonent bvkSh = new Abonent((byte) 0x01, (byte) 0x02, (byte) 64, (byte) 0, "BR_BVKsh");
        Abonent tsr_can = new Abonent((byte) 0x04, (byte) 0x02, (byte) 68, (byte) 0, "BR_TSR_CAN");
        Abonent j1939 = new Abonent((byte) 0x02, (byte) 0x01, (byte) 59, (byte) 0, "BR_J1939");
        Service protocol = new Service();
        protocol.addObmen(bvkSh);
        protocol.addObmen(tsr_can);
        protocol.addObmen(j1939);
        assertEquals(protocol.findByObmen("BR_J1939"), j1939); //exp, act
    }

    @Test
    public void whenAdd3ObmenAndNotFindNoOne() {
        Abonent bvkSh = new Abonent((byte) 0x01, (byte) 0x02, (byte) 64, (byte) 0, "BR_BVKsh");
        Abonent tsr_can = new Abonent((byte) 0x04, (byte) 0x02, (byte) 68, (byte) 0, "BR_TSR_CAN");
        Abonent j1939 = new Abonent((byte) 0x02, (byte) 0x01, (byte) 59, (byte) 0, "BR_J1939");
        Service protocol = new Service();
        protocol.addObmen(bvkSh);
        protocol.addObmen(tsr_can);
        protocol.addObmen(j1939);
        assertEquals(protocol.findByObmen("BR_NO_ABONENT"),null); //exp, act
        //assertThat(protocol.findByObmen("BR_NO_ABONENT"),null); // ->> NPE
    }

}