import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.event.*;


public class Gui {

    
    public static void main(String[] args) throws Exception {
        
        //Creating the Frame
        JFrame frame = new JFrame("Database Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);       
        
        JPanel pnRootPanel;

        JPanel pnTableDisplayPanel;
        JTable tbSQLTables = new JTable();
        JList lsSQLTableList;

        JPanel pnCustomQueryPanel;
        JButton btQuerySubmitBtn;
        JTextField tfQueryInput;
        JLabel lbQueryOutput;

        dbDAO dbConn = new dbDAO();

        pnRootPanel = new JPanel();
        GridBagLayout gbRootPanel = new GridBagLayout();
        GridBagConstraints gbcRootPanel = new GridBagConstraints();
        pnRootPanel.setLayout( gbRootPanel );

        pnTableDisplayPanel = new JPanel();
        pnTableDisplayPanel.setBorder( BorderFactory.createTitledBorder( "Database Tables Display" ) );
        GridBagLayout gbTableDisplayPanel = new GridBagLayout();
        GridBagConstraints gbcTableDisplayPanel = new GridBagConstraints();
        pnTableDisplayPanel.setLayout( gbTableDisplayPanel );

        lsSQLTableList = new JList(dbConn.getTableList().toArray());
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
                    
                    SQLTablesDisplayModel SQLtable = new SQLTablesDisplayModel(tableColumns.toArray(new String[0]), tableTuples);                    
                    tbSQLTables.setModel(SQLtable); 
                  }
                
            }
            
        }); 
        JScrollPane scpSQLTables = new JScrollPane( tbSQLTables );
        
        pnTableDisplayPanel.add( scpSQLTables );
        pnTableDisplayPanel.add( lsSQLTableList );
        pnRootPanel.add( pnTableDisplayPanel );

        pnCustomQueryPanel = new JPanel();
        pnCustomQueryPanel.setBorder( BorderFactory.createTitledBorder( "Custom Query Results" ) );
        GridBagLayout gbCustomQueryPanel = new GridBagLayout();
        GridBagConstraints gbcCustomQueryPanel = new GridBagConstraints();
        pnCustomQueryPanel.setLayout( gbCustomQueryPanel );

        btQuerySubmitBtn = new JButton( "Submit"  );
        pnCustomQueryPanel.add( btQuerySubmitBtn );

        tfQueryInput = new JTextField( );        
        pnCustomQueryPanel.add( tfQueryInput );

        lbQueryOutput = new JLabel( "" );
        btQuerySubmitBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String str = dbConn.printCustomQueryResult("SELECT * FROM customer;");
                    lbQueryOutput.setText(str);
                    dbConn.getTableList();
                    System.out.println(str);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });

        JScrollPane scpQueryOutput = new JScrollPane( lbQueryOutput );
       
       
        pnCustomQueryPanel.add( scpQueryOutput );
        pnRootPanel.add( pnCustomQueryPanel );


        frame.getContentPane().add(BorderLayout.CENTER, pnRootPanel);
        frame.setVisible(true);
    }
}