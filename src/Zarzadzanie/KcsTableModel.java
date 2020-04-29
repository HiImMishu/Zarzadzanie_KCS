package Zarzadzanie;

import javax.swing.table.AbstractTableModel;

public class KcsTableModel extends AbstractTableModel {
    private String[] headers = {"Lp.", "Kluczowe czynniki sukcesu", "Waga (0-1)", "Ocena (1-5)", "Wartość ważona"};
    private Object[][] data = {
            {"1.", "Poziom cen", "", "", ""},
            {"2.", "Jakość produktu", "", "", ""},
            {"3.", "Renoma firmy", "", "", ""},
            {"4.", "Referencje", "", "", ""},
            {"5.", "Jakość obsługi", "", "", ""},
            {"6.", "Termin realizacji projektów", "", "", ""},
            {"7.", "Okres udzielanych gwarancji", "", "", ""},
            {"8.", "Umiejętność organizowania i prowadzenia robót", "", "", ""},
            {"9.", "Wiarygodność techniczna", "", "", ""},
            {"10.", "Postrzegana wiarygodność ekonomiczna", "", "", ""},
            {"11.", "Kwalifikacja i doświadczenie pracowników", "", "", ""},
            {"12.", "Warunki płatności", "", "", ""},
            {"13.", "Zakres asortymentowy robót", "", "", ""},
            {"14.", "Średni poziom kosztów", "", "", ""},
            {"15.", "Wskaźniki rentowności", "", "", ""},
            {"16.", "Geograficzny zakres działania", "", "", ""},
            {"17.", "Możliwości kapitałowe podjęcia określonych robót", "", "", ""},
            {"18.", "Powiązania w branży", "", "", ""},
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
