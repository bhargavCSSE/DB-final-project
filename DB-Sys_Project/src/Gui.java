import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;


public class Gui {
    public static void main(String[] args) throws Exception {

        //Creating the Frame
        JFrame frame = new JFrame("Chat Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        JPanel pnGuiPanel;
        JList lsSQLTableList;
        JTable tbTableDisplay;
        JTextField tfUserInputString;
        JButton btSubmitButton;

        dbDAO dbConn = new dbDAO();


        pnGuiPanel = new JPanel();
        GridBagLayout gbGuiPanel = new GridBagLayout();
        GridBagConstraints gbcGuiPanel = new GridBagConstraints();
        pnGuiPanel.setLayout( gbGuiPanel );

        String []dataSQLTableList = { "Chocolate", "Ice Cream", "Apple Pie" };
        lsSQLTableList = new JList( dataSQLTableList );
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

        String [][]dataTableDisplay = new String[][] { new String[] {"11", "21"}, 
                                                    new String[] {"12", "22"}, 
                                                    new String[] {"13", "23"} };
        String []colsTableDisplay = new String[] { "", "" };
        tbTableDisplay = new JTable( dataTableDisplay, colsTableDisplay );
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