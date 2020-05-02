package Zarzadzanie;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class PoaView extends JPanel {
    private JTable table;
    private JLabel summaryLabel = new JLabel("(Ocena Maksymalna to 5.0)       OCENA UZYSKANA: ");
    private JLabel summary = new JLabel("");
    public PoaView() {
        createTable();
        PoaTableListener tableListener = new PoaTableListener(table, this);

        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(700);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(300);

        table.getTableHeader().setReorderingAllowed(false);
        table.setGridColor(Color.BLACK);
        table.getTableHeader().setPreferredSize(new Dimension(800, 30));

        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer column1Renderer = new DefaultTableCellRenderer();
        column1Renderer.setBackground(Color.green);
        DefaultTableCellRenderer column4Renderer = new DefaultTableCellRenderer();
        column4Renderer.setBackground(new Color(128,128,128));
        column4Renderer.setHorizontalAlignment(JLabel.CENTER);

        for(int i = 2; i < 4; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        }

        table.getColumnModel().getColumn(0).setCellRenderer(column1Renderer);
        table.getColumnModel().getColumn(4).setCellRenderer(column4Renderer);

        for(int i = 0; i < 15; i++) {
            table.setRowHeight(i,30);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 580));
        scrollPane.setMaximumSize(new Dimension(1000, 580));
        JPanel summaryHolder = new JPanel();
        summaryHolder.setLayout(new BoxLayout(summaryHolder, BoxLayout.X_AXIS));
        summaryHolder.add(summaryLabel);
        summaryHolder.add(summary);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(scrollPane);
        add(summaryHolder);
    }

    private void createTable() {
        table = new JTable(new PoaTableModel());
    }

    public void updateSummary(Object summary) {
        this.summary.setText(summary.toString());
    }
}
