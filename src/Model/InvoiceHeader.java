package Model;

//invoiceNum, invoiceDate, customerName, ArrayList<InvoiceLines>

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class InvoiceHeader{
    private int InvoiceNumber;
    private String CustomerName;
    private String InvoiceDate;
    private Double InvoiceTotal;
    private ArrayList<InvoiceLine> lines;



    public InvoiceHeader(int invNum, String invDate, String customerName) {
        this.InvoiceNumber = invNum;
        this.InvoiceDate = String.valueOf(invDate);
        this.CustomerName = customerName;
    }


    public int getInvNum() {
        return InvoiceNumber;
    }
    public void setInvNum(int invNum) {
        this.InvoiceNumber = invNum;
    }

    public String getInvDate() {
        return InvoiceDate;
    }
    public void setInvDate(String invDate) {
        this.InvoiceDate = invDate;
    }

    public String getCustomerName() {
        return CustomerName;
    }
    public void setCustomerName(String customerName) {
        this.CustomerName = customerName;
    }

    @Override
    public String toString() {
        String str="" + InvoiceNumber + ", " + CustomerName + ", " + InvoiceDate ;
        return str;
    }

    public ArrayList<InvoiceLine> getLines() {
        if (lines == null)
            lines = new ArrayList<>();
        return lines;
    }

   public void setLines(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }

    public double getInvoiceTotal() {
        double total = 0.0;
        for (InvoiceLine line : getLines()){
            total += line.getLineTotal();
        }
        return total;
    }

     public void addInvLine(InvoiceLine line){
        getLines().add(line);}


    public String getDataAsCSV() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return "" + getInvNum() + "," + df.format(getInvDate()) + "," + getCustomerName();
    }


}
