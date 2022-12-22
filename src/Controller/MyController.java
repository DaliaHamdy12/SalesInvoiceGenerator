package Controller;

import Model.HeaderModel;
import Model.InvoiceHeader;
import Model.InvoiceLine;
import Model.LineModel;
import View.ItemView;
import View.ListingFrame;
import View.NewInvoiceView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyController extends Component implements ActionListener, ListSelectionListener {
    private ListingFrame Frame;
    public java.text.DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private NewInvoiceView InvoiceFRAME;
    private ItemView ItemFRAME;



    public MyController(ListingFrame Frame) {
        this.Frame = Frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        System.out.println("Action Handlled");

        switch (e.getActionCommand())
        {
            case "CreateNewInvoice":
                System.out.println("Create New Invoice Action");
                CreateNewInvoice();
                break;

            case "DeleteInvoice":
                System.out.println("Delete Invoice Action");
                DeleteInvoiceFrame();
                break;

            case "createLineOK":
                System.out.println("Invoice OK Action");
                InvoiceOK();
                break;

            case "createLineCancel":
                System.out.println("Invoice Cancel Action");
                InvoiceCancel();
                break;

            case "Save":
                System.out.println("Save new item");
                SaveItem();
                break;

            case "Cancel":
                System.out.println("Cancel Item Action");
                CancelItem();
                break;

            case "ItemOK":
                System.out.println("Ok");
                ItemOK();
                break;

           case "ItemCancel":
                System.out.println("Item Cancel Action");
               ItemCancel();
                break;

            case "SaveFile":
                System.out.println("Save File Action");
                SaveFile();
                break;

            case "LoadFile":
                System.out.println("Load File Action");
                LoadFile();
                break;
        }
    }

    private void CreateNewInvoice() {
        InvoiceFRAME = new NewInvoiceView(Frame);
        InvoiceFRAME.setVisible(true);
        try {
            int invoiceNumber = 0;
            for (InvoiceHeader NewHeader : Frame.getInvoicesArray()) {
                if (NewHeader.getInvNum() > invoiceNumber)
                    invoiceNumber = NewHeader.getInvNum();
            }
            invoiceNumber++;

            InvoiceFRAME.getCustomerNameFD().setText("" + invoiceNumber);
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(Frame, " Select Invoice Header Files First", "Files Were Not Selected", JOptionPane.ERROR_MESSAGE);
            InvoiceFRAME.setVisible(false);
        }
    }
        private void DeleteInvoiceFrame() {

            int selectedInvoiceIndex = Frame.getInvoiceTable().getSelectedRow();
            if (selectedInvoiceIndex != -1) {
                Frame.getInvoicesArray().remove(selectedInvoiceIndex);
                Frame.getInvHeaderTableModel().fireTableDataChanged();

                Frame.getInvoiceItems().setModel(new LineModel(null));
                Frame.getCustomerNameTF().setText("");
                Frame.getInvoiceDateTF().setText("");
                Frame.getInvoiceNumber().setText("");
                Frame.getInvoiceTotal().setText("");

            }
            JOptionPane.showMessageDialog(null, "Invoice Deleted Successfully ! ");

        }

    private void InvoiceOK()
    {
        String CustomerName=InvoiceFRAME.getCustomerNameFD().getText();
        String DATEFORMATE =InvoiceFRAME.getInvoiceDateFD().getText();
        Date date = new Date();
        try {
            date = ListingFrame.df.parse(DATEFORMATE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(Frame, "Inalid Date Format ", "Invalid date format", JOptionPane.ERROR_MESSAGE);
        }
        int InvNumber=0;
        for(InvoiceHeader invoice:Frame.getInvoicesArray())
        {
            if(invoice.getInvNum()>InvNumber)
            {
                InvNumber=invoice.getInvNum();
            }
            InvNumber++;
        }
        InvoiceHeader voices=new InvoiceHeader(InvNumber,CustomerName,DATEFORMATE);
        Frame.getInvoicesArray().add(voices);
        Frame.getInvHeaderTableModel().fireTableDataChanged();
        InvoiceFRAME.setVisible(false);
        InvoiceFRAME.dispose();
        InvoiceFRAME=null;
    }

    private void InvoiceCancel()
    {
        InvoiceFRAME.setVisible(false);
        InvoiceFRAME.dispose();
        InvoiceFRAME=null;
    }

    private void SaveItem()
    {
        ItemFRAME = new ItemView(Frame);
        ItemFRAME.setVisible(true);
        try {
            int ItemNumber = 0;
            for (InvoiceHeader NewItem : Frame.getInvoicesArray()){
                if (NewItem.getInvNum()> ItemNumber)
                    ItemNumber = NewItem.getInvNum();
            }
            ItemNumber++;
        } catch (Exception exception)
        {
            JOptionPane.showMessageDialog(Frame," Load Invoices First ", "Files Werenot Selected", JOptionPane.ERROR_MESSAGE);
            ItemFRAME.setVisible(false);
        }
        try {
            int Item = 0;
            for (InvoiceLine NewIt : Frame.getItemArray()){
                if (NewIt.getItemCount()> Item)
                    Item = NewIt.getItemCount();
            }
            Item++;
        } catch (Exception exception)
        {
            JOptionPane.showMessageDialog(Frame," Choose Your Customer first & Save ", "Files Werenot Selected", JOptionPane.ERROR_MESSAGE);
            ItemFRAME.setVisible(false);
        }
    }



    private void ItemOK()
    {
        String item = ItemFRAME.getItemNameField().getText();
        String countStr = ItemFRAME.getItemCountField().getText();
        String priceStr = ItemFRAME.getItemPriceField().getText();

        int count=1 ;
        double price=1;
        try
        {
            count = Integer.parseInt(countStr);
            price = Double.parseDouble(priceStr);
        }
        catch (NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(Frame, "Cannot convert ", "Invalid number format", JOptionPane.ERROR_MESSAGE);
        }
        int selectedInvHeader = Frame.getInvoiceTable().getSelectedRow();
        if (selectedInvHeader != -1)
        {
            InvoiceHeader invoice = Frame.getInvoicesArray().get(selectedInvHeader);

            InvoiceLine line = new InvoiceLine(item, price, count, invoice);
            Frame.getItemArray().add(line);
            LineModel linesTableModel = (LineModel) Frame.getInvoiceItems().getModel();

            linesTableModel.fireTableDataChanged();
            Frame.getInvHeaderTableModel().fireTableDataChanged();
        }
        Frame.getInvoiceTable().setRowSelectionInterval(selectedInvHeader, selectedInvHeader);
        ItemFRAME.setVisible(false);
        ItemFRAME.dispose();
        ItemFRAME = null;
    }

    private void ItemCancel() {
        ItemFRAME.setVisible(false);
        ItemFRAME.dispose();
        ItemFRAME=null;
    }

    private void SaveFile() {
        ArrayList<InvoiceHeader> invoicesArray = Frame.getInvoicesArray();
        JFileChooser fileChooser = new JFileChooser();
        try {
            int result = fileChooser.showSaveDialog(Frame);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File headerFile = fileChooser.getSelectedFile();
                FileWriter HeaderFileWRITER = new FileWriter(headerFile);
                String headers = "";
                String lines = "";
                for (InvoiceHeader invoice : invoicesArray) {
                    headers += invoice.toString();
                    headers += "\n";
                    for (InvoiceLine line : invoice.getLines()) {
                        lines += line.getDataAsCSV();
                        lines += "\n";
                    }
                }

                headers = headers.substring(0, headers.length()-1);
                lines = lines.substring(0, lines.length()-1);
                result = fileChooser.showSaveDialog(Frame);
                File lineFile = fileChooser.getSelectedFile();
                FileWriter ItemsFileWRITER = new FileWriter(lineFile);
                HeaderFileWRITER.write(headers);
                ItemsFileWRITER.write(lines);
                HeaderFileWRITER.close();
                ItemsFileWRITER.close();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    private void LoadFile()
    {
        JOptionPane.showMessageDialog(Frame, "Choose Invoice Header File ", "Attention", JOptionPane.WARNING_MESSAGE);
        JFileChooser Choose = new JFileChooser();
        try {
            int result = Choose.showOpenDialog(Frame);
            if (result == JFileChooser.APPROVE_OPTION)
            {
                File Header=Choose.getSelectedFile();
                String headerStrpath=Header.getAbsolutePath();
                Path headerPath= Paths.get(headerStrpath);


                List<String>HeaderLines=Files.readAllLines(headerPath);
                ArrayList<InvoiceHeader> InvHeaders =new ArrayList<>();
                for (String headerLine:HeaderLines)
                {
                    String [] Part =headerLine.split(",");
                    String  Part1 = Part[0];
                    String  Part2 = Part[1];
                    String  Part3 = Part[2];

                    int Code = Integer.parseInt(Part1);
                    Date invoiceDate=DateFormat.parse(Part2);
                    InvoiceHeader in = new InvoiceHeader(Code,Part3,Part2);
                    InvHeaders.add(in);
                }
                Frame.setInvheaders(InvHeaders);

                JOptionPane.showMessageDialog(Frame, "Choose Invoice Line File ", "Attention", JOptionPane.WARNING_MESSAGE);

                result = Choose.showOpenDialog(Frame);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File Item=Choose.getSelectedFile();
                    Path ItemPath= Paths.get(Item.getAbsolutePath());


                    List<String>ItemLines=Files.readAllLines(ItemPath);
                    ArrayList<InvoiceLine> InvItem =new ArrayList<>();
                    for (String itemline :ItemLines)
                    {
                        String[] Part =itemline.split(",");
                        String  ITPart1 = Part[0];
                        String  ITPart2 = Part[1];
                        String  ITPart3 = Part[2];
                        String  ITPart4 = Part[3];

                        int InvCode = Integer.parseInt(ITPart1);
                        double Price =Double.parseDouble(ITPart3);
                        int count = Integer.parseInt(ITPart4);
                        InvoiceHeader In =Frame.getHeaderObject(InvCode);

                        InvoiceLine line =new InvoiceLine(ITPart2,Price,count,In);
                        In.getLines().add(line);
                    }
                }
                HeaderModel InvoicesHeaderTable= new HeaderModel(InvHeaders);
                Frame.setHeaderTable(InvoicesHeaderTable);
                Frame.getInvoiceTable().setModel(InvoicesHeaderTable);

                System.out.println("file loaded");
            }
        }
        catch (IOException ex)
        {
            Logger.getLogger(MyController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(Frame, "Cannot read file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (ParseException ex)
        {
            Logger.getLogger(MyController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(Frame, "Cannot read file", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }


    private void CancelItem()
    {
        int lineIndex = Frame.getInvoiceItems().getSelectedRow();
        int selectedInvoiceIndex = Frame.getInvoiceItems().getSelectedRow();
        int InvoiceIndex = Frame.getInvoiceItems().getSelectedRow();
        if (selectedInvoiceIndex != -1)
        {
            InvoiceLine line = Frame.getInvLineTableModel().getInvoiceLines().get(lineIndex);
            Frame.getInvLineTableModel().getInvoiceLines().remove(lineIndex);

            Frame.getInvHeaderTableModel().fireTableDataChanged();
            Frame.getInvLineTableModel().fireTableDataChanged();
            Frame.getInvoiceTotal().setText("" + line.getHeader().getInvoiceTotal());
            JOptionPane.showMessageDialog(null, "Line Deleted Successfully ! ");
            displayInvoices();
        }
    }


    private void displayInvoices(){
        for (InvoiceLine lines :Frame.getItemArray()) {
            System.out.println(lines);
        }
    }



    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        int selectedInvItem = Frame.getInvoiceTable().getSelectedRow();
        int selectedInvIndex = Frame.getInvoiceTable().getSelectedRow();
        System.out.println("Invoice selected:" + selectedInvIndex);
        if (selectedInvIndex != -1  ) {
            InvoiceHeader selectedInv = Frame.getInvoicesArray().get(selectedInvIndex);

            ArrayList<InvoiceLine> lines = selectedInv.getLines();
            LineModel lineTableModel = new LineModel(lines);
            Frame.setItemArray(lines);
            Frame.getInvoiceItems().setModel(lineTableModel);
            Frame.getCustomerNameTF().setText(selectedInv.getCustomerName());
            Frame.getInvoiceNumber().setText("" + selectedInv.getInvNum());
            Frame.getInvoiceTotal().setText("" + selectedInv.getInvoiceTotal());
            Frame.getInvoiceDateTF().setText(selectedInv.getInvDate());

        }

    }

 }






