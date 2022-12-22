package Model;

import javax.swing.table.AbstractTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HeaderModel extends AbstractTableModel {

    private ArrayList<InvoiceHeader> invoicesArray;
    private DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    public HeaderModel(List<InvoiceHeader> invoicesArray) {
        this.invoicesArray = (ArrayList<InvoiceHeader>) invoicesArray;

    }


    @Override
    public int getRowCount() {
        return invoicesArray.size();
    }

    public List<InvoiceHeader> getInvoicesArray() {
        return invoicesArray;
    }


    @Override
    public int getColumnCount() {
        return 4;
    }


    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "No.";
            case 1:
                return "Customer";
            case 2:
                return "Date";
            case 3:
                return "Total";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int Row, int Column) {

        InvoiceHeader InvoicesArrayLst = invoicesArray.get(Row);
        switch (Column)
        {
            case 0: return InvoicesArrayLst.getInvNum();
            case 1: return InvoicesArrayLst.getInvDate();
            case 2: return InvoicesArrayLst.getCustomerName();
            case 3: return InvoicesArrayLst.getInvoiceTotal();
            default : return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return Double.class;
            default:
                return Object.class;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
