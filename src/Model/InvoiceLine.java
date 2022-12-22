package Model;



//itemName, itemPrice, and count,
public class InvoiceLine {
    private InvoiceHeader Invoice;
    private String itemName;
    private double itemPrice;
    private int itemCount;
    private double lineTotal;
    private InvoiceHeader header;

    public InvoiceLine( String itemName, double itemPrice, int itemCount, InvoiceHeader header) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.header = header;
        this.setLineTotal(this.itemCount*this.itemPrice);
    }

    public InvoiceHeader getHeader() {
        return header;
    }

    public void setHeader(InvoiceHeader header) {
        this.header = header;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getLineTotal(){
        return lineTotal;
    }

    private void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public String getDataAsCSV() {
        return "" + getHeader().getInvNum() + "," + getItemName() + "," + getItemPrice() + "," + getItemCount();
    }


}