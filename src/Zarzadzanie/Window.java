package Zarzadzanie;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private final int MINIMUM_WIDTH = 1000;
    private final int MINIMUM_HEIGHT = 700;

    Window() {
        setMinimumSize(new Dimension(MINIMUM_WIDTH, MINIMUM_HEIGHT));
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }
}
