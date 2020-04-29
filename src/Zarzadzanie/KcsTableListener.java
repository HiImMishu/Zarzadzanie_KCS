package Zarzadzanie;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class KcsTableListener implements TableModelListener {
    private KcsView view;
    private JTable table;

    KcsTableListener(JTable table, KcsView kcsView) {
        this.table = table;
        this.view = kcsView;
        table.getModel().addTableModelListener(this);
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();
        if(column == 4)
            return;
        KcsTableModel model = (KcsTableModel) e.getSource();
        String columnName = model.getColumnName(column);
        Object changed = model.getValueAt(row, column);
        Object data;

        if(column == 2)
            data = model.getValueAt(row, column+1);
        else
            data = model.getValueAt(row, column-1);

        if(!data.equals(""))
            model.setValueAt((Object)(Double.parseDouble(data.toString()) * Double.parseDouble(changed.toString())), row, 4);
        avg();
    }

    private void avg() {
        double sum = 0;
        double counter = 0;
        for(int i = 0; i < table.getRowCount(); i++) {
            if(!table.getModel().getValueAt(i, 4).equals("")) {
                sum += Double.parseDouble(table.getModel().getValueAt(i, 4).toString());
                counter++;
            }
            if(counter != 0)
                view.updateSummary(sum/counter);
        }
    }
}
