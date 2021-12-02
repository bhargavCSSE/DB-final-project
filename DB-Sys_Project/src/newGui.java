
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class newGui extends JPanel {
    private JList lsSQLTableList;
    private JButton btQuerySubmitBtn;
    private JTextField tfQueryInput;
    private JTextArea lbQueryOutput;
    private JTable tbSQLTables;

    public newGui() throws Exception {    

        //construct components
        dbDAO dbConn = new dbDAO();
        lsSQLTableList = new JList(dbConn.getTableList().toArray());;
        btQuerySubmitBtn = new JButton ("Submit");
        tfQueryInput = new JTextField (5);
        lbQueryOutput = new JTextArea (5, 5);
        tbSQLTables = new JTable();
        

        //set component bounds (only needed by Absolute Positioning)
        lsSQLTableList.setBounds (40, 15, 120, 205);
        btQuerySubmitBtn.setBounds (40, 245, 130, 35);
        tfQueryInput.setBounds (175, 250, 480, 25);
        lbQueryOutput.setBounds (45, 285, 610, 115);
        tbSQLTables.setBounds (170, 15, 480, 205);
        
        lsSQLTableList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String desiredTable = lsSQLTableList.getSelectedValue().toString();
                    List<String> tableColumns = new ArrayList<String>();
                    List<String[]> tableTuples = new ArrayList<String[]>();
                    try {
                        tableColumns = dbConn.getTableColumnList(desiredTable);
                        tableTuples = dbConn.getTableTuples(desiredTable, tableColumns.toArray(new String[0]));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    System.out.println(tableColumns.toArray(new String[0]));
                    SQLTablesDisplayModel SQLtable = new SQLTablesDisplayModel(tableColumns.toArray(new String[0]), tableTuples);                    
                    tbSQLTables.setModel(SQLtable); 
                  }
                
            }
            
        }); 

        btQuerySubmitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String queryStr = tfQueryInput.getText();
                    String str = dbConn.printCustomQueryResult(queryStr);
                    lbQueryOutput.setText(str);
                    dbConn.getTableList();
                    System.out.println(str);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });


        //adjust size and set layout
        setPreferredSize (new Dimension (681, 407));
        setLayout (null);

        //add components
        add (lsSQLTableList);
        add (btQuerySubmitBtn);
        add (tfQueryInput);
        add (lbQueryOutput);
        add (tbSQLTables);
    }


    public static void main (String[] args) throws Exception {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new newGui());
        frame.pack();
        frame.setVisible (true);
    }
}