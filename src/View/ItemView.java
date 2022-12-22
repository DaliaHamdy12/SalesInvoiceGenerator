package View;

import javax.swing.*;
import java.awt.*;

public class ItemView extends JDialog {
    private JTextField  itemNameField, itemCountField, itemPriceField;
    private JLabel  itemNameLbl, itemCountLbl, itemPriceLbl;
    private JButton okBtn, cancelBtn ;
    public ItemView(ListingFrame frame) {

        itemNameLbl = new JLabel("Item Name");
        add(itemNameLbl);
        itemNameField = new JTextField(20);
        add(itemNameField);

        itemCountLbl = new JLabel("Item Count");
        add(itemCountLbl);
        itemCountField = new JTextField(20);
        add(itemCountField);

        itemPriceLbl = new JLabel("Item Price");
        add(itemPriceLbl);
        itemPriceField = new JTextField(20);
        add(itemPriceField);

        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");

        okBtn.setActionCommand("ItemOK");
        cancelBtn.setActionCommand("ItemCancel");

        okBtn.addActionListener(frame.getListener());
        cancelBtn.addActionListener(frame.getListener());
        setLayout(new GridLayout(4, 2));

        add(okBtn);
        add(cancelBtn);

        pack();

    }

    public  JTextField getItemNameField() {
        return itemNameField;
    }

    public  JTextField getItemCountField() {
        return itemCountField;
    }

    public  JTextField getItemPriceField() {
        return itemPriceField;
    }


}
