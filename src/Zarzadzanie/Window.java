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
        setLocationRelativeTo(null);

        KcsView kcsV = new KcsView();
        kcsV.setSize(this.getWidth(), this.getHeight());

        PoaView poaV = new PoaView();
        poaV.setSize(this.getWidth(), this.getHeight());

        GraphCreator graphCreator = new GraphCreator();
        graphCreator.setSize(this.getWidth(), this.getHeight());

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add(kcsV, "KLUCZOWE CZYNNIKI SUKCESU");
        tabbedPane.add(poaV, "PUNKTOWA OCENA ATRAKCYJNOSCI");
        tabbedPane.add(graphCreator, "WYKRES");

        add(tabbedPane);
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
