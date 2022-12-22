package View;

import Controller.MyController;
import Model.HeaderModel;
import Model.InvoiceHeader;
import Model.InvoiceLine;
import Model.LineModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ListingFrame extends JFrame {

    private JTable InvoiceTable, InvoiceItems;
    private JMenuBar mb;
    private JScrollPane JScrollPane1, JScrollPane2;
    private JLabel InvoiceNumberLBL, InvoiceNumber, InvoiceDate, CustomerName, InvoiceTotal, InvoiceTotalLBL, lblTest, jLabel6;
    private JMenu File;
    private JMenuItem LoadFile, SaveFile;
    private JButton CreateNewInvoice, Delete, Save, Cancel;
    private JTextField InvoiceDateTF, CustomerNameTF;
    private HeaderModel invHeaderTableModel;
    private LineModel invLineTableModel;


    public ListingFrame() {
            super("Sales Invoice Generator");
            setLayout(new FlowLayout());
            setSize(1500, 600);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            initComponents();
    }
    private void initComponents(){



            mb = new JMenuBar();
            File = new JMenu("File");
            LoadFile = new JMenuItem("Load File");
            LoadFile.addActionListener(listener);
            LoadFile.setActionCommand("LoadFile");
            LoadFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadFileActionPerformed(evt);
            }
            private void LoadFileActionPerformed(java.awt.event.ActionEvent evt){}

        });
            File.add(LoadFile);
            SaveFile = new JMenuItem("Save File");
            SaveFile.addActionListener(listener);
            SaveFile.setActionCommand("SaveFile");
            SaveFile.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {SaveFileActionPerformed(evt);}
                private void SaveFileActionPerformed(java.awt.event.ActionEvent evt){}
            });


            File.add(SaveFile);
            mb.add(File);
            setJMenuBar(mb);


            InvoiceNumberLBL = new JLabel("Invoice Number");
            InvoiceNumber = new JLabel();

            lblTest = new JLabel("Invoice Items");

            InvoiceDate = new JLabel("Invoice Date");
            InvoiceDateTF = new JTextField(30);

            jLabel6 = new JLabel("Invoice Table");


            CustomerName = new JLabel("Customer Name");
            CustomerNameTF = new JTextField(30);

            InvoiceTotalLBL = new JLabel("Invoice Total");
            InvoiceTotal = new JLabel();


            JScrollPane1 = new JScrollPane();
            JScrollPane2 = new JScrollPane();


            CreateNewInvoice = new JButton("Create New Invoice");
            CreateNewInvoice.setActionCommand("CreateNewInvoice");
            CreateNewInvoice.addActionListener(listener);
            CreateNewInvoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateNewInvoiceActionPerformed(evt);
            }
            private void CreateNewInvoiceActionPerformed(java.awt.event.ActionEvent evt){}

        });
            Delete = new JButton("Delete Invoice");
            Delete.setActionCommand("DeleteInvoice");
            Delete.addActionListener(listener);
            Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
            private void DeleteActionPerformed(java.awt.event.ActionEvent evt){}
        });
            Save = new JButton("Create Item");
            Save.setActionCommand("Save");
            Save.addActionListener(listener);
            Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }
            private void SaveActionPerformed(java.awt.event.ActionEvent evt){}

        });
            Cancel = new JButton("Delete  Item");
            Cancel.addActionListener(listener);
            Cancel.setActionCommand("Cancel");

        InvoiceItems = new JTable();
        InvoiceItems.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        InvoiceItems.setModel(new DefaultTableModel());
        for (String s : new String[]{"No.", "Item Name", "Item Price", "Count", "Item Total"}) {
        }
        InvoiceItems.setShowGrid(true);
        JScrollPane1.setViewportView(InvoiceItems);
        InvoiceItems.getAccessibleContext().setAccessibleName("");

            InvoiceTable = new JTable();
            InvoiceTable.getSelectionModel().addListSelectionListener(listener);
            InvoiceTable.setModel(new DefaultTableModel());
            for (String s : new String[]{"No.", "Date", "Customer", "Total"}) {
            }
            InvoiceTable.setShowGrid(true);
            JScrollPane2.setViewportView(InvoiceTable);
            InvoiceTable.getAccessibleContext().setAccessibleName("");


        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(JScrollPane2, GroupLayout.PREFERRED_SIZE, 500, GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(lblTest)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(InvoiceNumberLBL)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(InvoiceNumber, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(JScrollPane1, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(CustomerName)
                                                        .addComponent(InvoiceTotalLBL)
                                                        .addComponent(InvoiceDate))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(InvoiceDateTF)
                                                        .addComponent(CustomerNameTF)
                                                        .addComponent(InvoiceTotal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(21, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(CreateNewInvoice)
                                .addGap(30, 30, 30)
                                .addComponent(Delete)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Save)
                                .addGap(62, 62, 62)
                                .addComponent(Cancel)
                                .addGap(111, 111, 111))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JScrollPane2,GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(InvoiceNumberLBL)
                                                        .addComponent(InvoiceNumber))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(InvoiceDate)
                                                        .addComponent(InvoiceDateTF,GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(CustomerName)
                                                        .addComponent(CustomerNameTF, GroupLayout.PREFERRED_SIZE,GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                        .addComponent(InvoiceTotalLBL)
                                                        .addComponent(InvoiceTotal))
                                                .addGap(30, 30, 30)
                                                .addComponent(lblTest)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(JScrollPane1,GroupLayout.PREFERRED_SIZE, 208,GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(CreateNewInvoice)
                                        .addComponent(Delete)
                                        .addComponent(Save)
                                        .addComponent(Cancel))
                                .addContainerGap(21, Short.MAX_VALUE))
        );


        }
    MyController listener = new MyController(this);
    public MyController getListener() {
        return listener;
    }
    public static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");


    public void setInvoiceTable(JTable invLineTable) {
        this.InvoiceTable = invLineTable;
    }

    public void setInvHeaderTableModel(HeaderModel invHeaderTableModel) {
        this.invHeaderTableModel = invHeaderTableModel;
    }

    public JLabel getInvoiceNumber() {
        return InvoiceNumber;
    }
    public JLabel getInvoiceTotal() {
        return InvoiceTotal;
    }

    public JTextField getCustomerNameTF() {
        return CustomerNameTF;
    }
    public JTextField getInvoiceDateTF() {return InvoiceDateTF;}

    public JTable getInvoiceTable() {return InvoiceTable;}
    public JTable getInvoiceItems() {return InvoiceItems;}

    public ArrayList<InvoiceHeader> invoicesArray;
    public ArrayList<InvoiceHeader> getInvoicesArray() {return invoicesArray;}
    public void setInvheaders(ArrayList<InvoiceHeader> invoices) {
        this.invoicesArray = invoices;
        invHeaderTableModel= new HeaderModel(invoicesArray);
        this.InvoiceTable.setModel(invHeaderTableModel);
    }
    public InvoiceHeader getHeaderObject(int Code)  {
        for  (InvoiceHeader inv :invoicesArray){
            if (inv.getInvNum() == Code){
                return inv; }
        }
        return null;
    }
    public ArrayList<InvoiceLine> ItemArray;
    public ArrayList<InvoiceLine> getItemArray() {return ItemArray;}
    public void setItemArray(ArrayList<InvoiceLine> Items){
        this.ItemArray = Items;
        invLineTableModel= new LineModel(ItemArray);
        this.InvoiceItems.setModel(invLineTableModel);
    }

    public HeaderModel getInvHeaderTableModel() {return invHeaderTableModel;}
    public LineModel getInvLineTableModel() {return invLineTableModel;}

    public void setHeaderTable(HeaderModel HeaderTable) {
        this.invHeaderTableModel = HeaderTable;
    }

    }

