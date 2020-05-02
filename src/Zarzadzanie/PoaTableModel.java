package Zarzadzanie;

import javax.swing.table.AbstractTableModel;

public class PoaTableModel extends AbstractTableModel {
    private String[] headers = {"Lp.", "Kryterium oceny sektora", "Waga (1 - 3)", "Ocena (1-5)", "Wartość ważona"};
    private Object[][] data = {
            {"1.", "Wielkość rynku", "", "", ""},
            {"2.", "Przewidywana stopa wzrostu", "", "", ""},
            {"3.", "Rentownaść sektora", "", "", ""},
            {"4.", "Stopień koncentracji sektora", "", "", ""},
            {"5.", "Ostrość walki konkurencyjnej", "", "", ""},
            {"6.", "Wysokość barier wejścia", "", "", ""},
            {"7.", "Wysokość barier wyjścia", "", "", ""},
            {"8.", "Groźba pojawienia się substytutów", "", "", ""},
            {"9.", "Groźba pojawienia się nowych konkurentów", "", "", ""},
            {"10.", "Pewność zaopatrzenia", "", "", ""},
            {"11.", "Stabilność technologiczna", "", "", ""},
            {"12.", "Możliwość różnicowania produktów", "", "", ""},
            {"13.", "Możliwość dywersyfikowania działalności", "", "", ""},
            {"14.", "Sezonowość i cykliczność", "", "", ""},
            {"15.", "Zagrożenie środowiska przyrodniczego", "", "", ""}
    };

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex < 2 || columnIndex > 3)
            return false;
        return true;
    }

    @Override
    public String getColumnName(int column) {
        return headers[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}
