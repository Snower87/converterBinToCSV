package ru.begletsov.converter;

import javax.swing.*;
import java.awt.*;

public class GUIConv extends JFrame {
    private JLabel text1;

    public GUIConv() {
        super("Заголовок окна");
        setLayout(new FlowLayout());
        text1 = new JLabel("Пробный текст");
        text1.setToolTipText("Просто так");
        add(text1);
    }
}
