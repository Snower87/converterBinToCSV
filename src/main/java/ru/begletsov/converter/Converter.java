package ru.begletsov.converter;

import javax.swing.*;

public class Converter {
    public static String test() {
        return "test";
    }

    public static void main(String[] args) {
        GUIConv guiConv = new GUIConv();
        guiConv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiConv.setSize(400, 200);
        guiConv.setVisible(true);
    }
}
