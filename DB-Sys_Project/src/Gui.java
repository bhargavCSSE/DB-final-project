import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;


public class Gui {

    
    public static void main(String[] args) throws Exception {

        //Creating the Frame
        JFrame frame = new JFrame("Database Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel pnGuiPanel;
        JList lsSQLTableList;
        final JTable tbTableDisplay = new JTable();
        JTextField tfUserInputString;
        JButton btSubmitButton;

        dbDAO dbConn = new dbDAO();


        pnGuiPanel = new JPanel();
        GridBagLayout gbGuiPanel = new GridBagLayout();
        GridBagConstraints gbcGuiPanel = new GridBagConstraints();
        pnGuiPanel.setLayout( gbGuiPanel );

        
        lsSQLTableList = new JList(dbConn.getTableList().toArray());
        lsSQLTableList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String desiredTable = lsSQLTableList.getSelectedValue().toString();
                    List<String> tableColumns = new ArrayList<String>();
                    try {
                        tableColumns = dbConn.getTableColumnList(desiredTable);
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    List<String[]> tableTuples = new ArrayList<String[]>();
                    try {
                        tableTuples = dbConn.getTableTuples(desiredTable, tableColumns.toArray(new String[0]));
                    } catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    SQLTablesDisplayModel SQLtable = new SQLTablesDisplayModel(tableColumns.toArray(new String[0]), tableTuples);                    
                    tbTableDisplay.setModel(SQLtable); 
                  }
                
            }
            
        }); 

        
        gbcGuiPanel.gridx = 1;
        gbcGuiPanel.gridy = 2;
        gbcGuiPanel.gridwidth = 6;
        gbcGuiPanel.gridheight = 12;
        gbcGuiPanel.fill = GridBagConstraints.BOTH;
        gbcGuiPanel.weightx = 1;
        gbcGuiPanel.weighty = 1;
        gbcGuiPanel.anchor = GridBagConstraints.NORTH;
        gbGuiPanel.setConstraints( lsSQLTableList, gbcGuiPanel );
        pnGuiPanel.add( lsSQLTableList );

        
        
        JScrollPane scpTableDisplay = new JScrollPane( tbTableDisplay );
        gbcGuiPanel.gridx = 8;
        gbcGuiPanel.gridy = 2;
        gbcGuiPanel.gridwidth = 11;
        gbcGuiPanel.gridheight = 12;
        gbcGuiPanel.fill = GridBagConstraints.BOTH;
        gbcGuiPanel.weightx = 1;
        gbcGuiPanel.weighty = 1;
        gbcGuiPanel.anchor = GridBagConstraints.NORTH;
        gbGuiPanel.setConstraints( scpTableDisplay, gbcGuiPanel );
        pnGuiPanel.add( scpTableDisplay );

        tfUserInputString = new JTextField( );
        gbcGuiPanel.gridx = 4;
        gbcGuiPanel.gridy = 15;
        gbcGuiPanel.gridwidth = 15;
        gbcGuiPanel.gridheight = 4;
        gbcGuiPanel.fill = GridBagConstraints.BOTH;
        gbcGuiPanel.weightx = 1;
        gbcGuiPanel.weighty = 0;
        gbcGuiPanel.anchor = GridBagConstraints.NORTH;
        gbGuiPanel.setConstraints( tfUserInputString, gbcGuiPanel );
        pnGuiPanel.add( tfUserInputString );

        btSubmitButton = new JButton( "Submit"  );
        btSubmitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    dbConn.printQueryResult();
                    dbConn.getTableList();
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
        });
        gbcGuiPanel.gridx = 1;
        gbcGuiPanel.gridy = 16;
        gbcGuiPanel.gridwidth = 2;
        gbcGuiPanel.gridheight = 2;
        gbcGuiPanel.fill = GridBagConstraints.BOTH;
        gbcGuiPanel.weightx = 1;
        gbcGuiPanel.weighty = 0;
        gbcGuiPanel.anchor = GridBagConstraints.NORTH;
        gbGuiPanel.setConstraints( btSubmitButton, gbcGuiPanel );
        pnGuiPanel.add( btSubmitButton );

        // //Adding Components to the frame.
        //frame.getContentPane().add(BorderLayout.SOUTH, gbcPanel0);
        frame.getContentPane().add(BorderLayout.CENTER, pnGuiPanel);
        //frame.getContentPane().add(BorderLayout.CENTER, gbPanel0);
        frame.setVisible(true);

    }
}