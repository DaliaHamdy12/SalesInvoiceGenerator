package View;

import javax.swing.*;
import java.awt.*;

public class NewInvoiceView extends JDialog {
    private JTextField CustomerNameFD, InvoiceDateFD;
    private JLabel CustomerNameLBL,InvoiceDateLBL;
    private JButton  okBtn2 , cancelBtn2;
    public NewInvoiceView(ListingFrame frame) {

        CustomerNameLBL = new JLabel("Customer Name:");
        add(CustomerNameLBL);
        InvoiceDateLBL = new JLabel("Invoice Date:");
        add(InvoiceDateLBL);
        CustomerNameFD = new JTextField(20);
        add(CustomerNameFD);
        InvoiceDateFD = new JTextField(20);
        add(InvoiceDateFD);


        okBtn2 = new JButton("OK");
        cancelBtn2 = new JButton("Cancel");

        okBtn2.setActionCommand("createLineOK");
        cancelBtn2.setActionCommand("createLineCancel");

        okBtn2.addActionListener(frame.getListener());
        cancelBtn2.addActionListener(frame.getListener());
        setLayout(new GridLayout(4, 2));

        add(okBtn2);
        add(cancelBtn2);

        pack();

    }

    public JTextField getCustomerNameFD() {
        return CustomerNameFD;
    }

    public JTextField getInvoiceDateFD() {
        return InvoiceDateFD;
    }



}


