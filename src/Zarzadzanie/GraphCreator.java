package Zarzadzanie;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.statistics.Regression;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;

public class GraphCreator extends JPanel {
    private JTable table;
    private ChartPanel chartPanel;
    private JPanel placeholder = new JPanel();
    private PriceTableModel tablemodel = new PriceTableModel();
    private JLabel equation = new JLabel("Równanie linii trendu: ");

    GraphCreator() {
        table = new JTable(tablemodel);
        JLabel tableInfo = new JLabel("Wypełnij nazwę spółki oraz ceny akcji zamknięcia w kwartałach przebiegu czasowego");
        tableInfo.setPreferredSize(new Dimension(800, 50));
        tableInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        equation.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton drawChart = new JButton("WYKRES");
        drawChart.addActionListener(event -> {
            createChart();
            repaint();
        });
        drawChart.setAlignmentX(Component.CENTER_ALIGNMENT);

        placeholder.setMaximumSize(new Dimension(1000, 500));
        placeholder.setBackground(Color.darkGray);

        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getTableHeader().setReorderingAllowed(false);
        table.setGridColor(Color.BLACK);
        table.getTableHeader().setPreferredSize(new Dimension(800, 30));
        table.getTableHeader().setBackground(Color.gray);

        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer column1Renderer = new DefaultTableCellRenderer();
        column1Renderer.setHorizontalAlignment(JLabel.CENTER);
        column1Renderer.setBackground(Color.green);

        for (int i = 1; i < table.getModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        }

        table.getColumnModel().getColumn(0).setCellRenderer(column1Renderer);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1000, 65));
        scrollPane.setMaximumSize(new Dimension(1000, 65));

        add(Box.createRigidArea(new Dimension(0, 10)));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(tableInfo);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(scrollPane);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(placeholder);
        add(Box.createRigidArea(new Dimension(0, 30)));
        add(equation);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(drawChart);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        TableModel model = table.getModel();
        for (int i = 1; i < model.getColumnCount(); i++) {
            dataset.addValue(Double.parseDouble(model.getValueAt(0, i).toString()), model.getValueAt(0, 0).toString(), model.getColumnName(i));
        }
        return dataset;
    }

    public void createChart() {
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                table.getModel().getValueAt(0, 0).toString(),
                "Kwartały w latach", "Ceny akcji (zł)",
                tablemodel.createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        chartPanel = new ChartPanel(lineChart);
        chartPanel.setSize(new Dimension(990, 490));
        chartPanel.setPreferredSize(new Dimension(990, 490));
        placeholder.removeAll();
        placeholder.add(chartPanel, BorderLayout.CENTER);
        placeholder.revalidate();
        placeholder.repaint();

        double[] regressionData = Regression.getOLSRegression(tablemodel.createDataset(), 0);
        LinearRegression lr = new LinearRegression(tablemodel.getX(), tablemodel.getY());
        equation.setText("Równanie linii trendu: y = " + String.format("%.4f", regressionData[1]) + "x + " + String.format("%.4f", regressionData[0]) + "        Współczynnik R^2 = "+String.format("%.4f", lr.R2()));
    }
}
