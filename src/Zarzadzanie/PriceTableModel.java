package Zarzadzanie;

import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.table.AbstractTableModel;
import java.util.function.DoubleBinaryOperator;

public class PriceTableModel extends AbstractTableModel {
    private String[] headers = {"Nazwa spółki", "1Q 2017", "2Q 2017", "3Q 2017", "4Q 2017", "1Q 2018", "2Q 2018", "3Q 2018", "4Q 2018", "1Q 2019", "2Q 2019", "3Q 2019", "4Q 2019"};
    private Object[] data = {"", "", "", "", "", "", "", "", "", "", "", "", ""};

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public XYDataset createDataset() {
        XYSeries series = new XYSeries("Data");
        for (int i = 1; i < data.length; i++) {
            series.add(i, Double.parseDouble(data[i].toString()));
        }
        XYSeriesCollection xyData = new XYSeriesCollection(series);
        double[] coefficients = Regression.getOLSRegression(xyData, 0);
        double b = coefficients[0]; // intercept
        double m = coefficients[1]; // slope
        XYSeries trend = new XYSeries("Trend");
        double x = series.getDataItem(0).getXValue();
        trend.add(x, m * x + b);
        x = series.getDataItem(series.getItemCount() - 1).getXValue();
        trend.add(x, m * x + b);
        xyData.addSeries(trend);
        return xyData;
    }

    public double[] getY() {
        double[] d = new double[data.length-1];
        for(int i = 1; i<data.length; i++) {
            d[i-1] = Double.parseDouble(data[i].toString());
        }
        return d;
    }

    public double[] getX() {
        double[] d = new double[data.length-1];
        for(int i = 1; i<data.length; i++) {
            d[i-1] = i;
        }
        return d;
    }
}
