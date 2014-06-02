package gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import databaseConnection.DBConnection;
import exceptions.ConnectionFailedException;
import exceptions.InitializeException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class UHasAtt extends JPanel {
	private JTextField UID;
	private JTextField AID;
	private JTable table;
	private JLabel lblenterUID;
	private JLabel lblNewLabel;
	private JLabel lblstored;

	/**
	 * Create the panel.
	 */
	public UHasAtt() {
		
		JLabel lblUID = new JLabel("User ID");
		
		UID = new JTextField();
		UID.setColumns(10);
		
		JLabel lblAID = new JLabel("Attribute ID");
		
		AID = new JTextField();
		AID.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(UID.getText().length()==0 || AID.getText().length()==0){
				if(UID.getText().length()==0 )
					lblenterUID.setVisible(true);
				if(AID.getText().length()==0)
					lblNewLabel.setVisible(true);
					
					
			}
			else{
				try {

					DBConnection.AtoU(Integer.parseInt(UID.getText()) ,Integer.parseInt(AID.getText()) );
					lblstored.setVisible(true);
					String[][] s = DBConnection.UhasA();
					String[] c = new String[] {
							"UID", "AID"
						};
					TableModel dataModel = new DefaultTableModel(s,c);
					table.setModel(dataModel );
				
				}
				 catch (ConnectionFailedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InitializeException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
				
			}
				
		
			
			}});
		try{
	    String[][] s = DBConnection.UhasA();
		String[] c = new String[] {
				 "UID","AID"
			};
		
		table = new JTable(s,c);
		TableModel dataModel = new DefaultTableModel(s,c);
		table.setModel(dataModel );
		}
		catch(Exception e){
		e.printStackTrace();	
		}
		
		lblenterUID = new JLabel("\"Enter UID\"");
		lblenterUID.setForeground(Color.RED);
		lblenterUID.setVisible(false);
		
		lblNewLabel = new JLabel("\"Enter AID\"");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setVisible(false);
		
		
		lblstored = new JLabel("\"Stored\"");
		lblstored.setVisible(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUID)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(UID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblAID))
								.addComponent(lblenterUID))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(AID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnSave)
									.addGap(18)
									.addComponent(lblstored))
								.addComponent(lblNewLabel))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUID)
						.addComponent(UID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAID)
						.addComponent(AID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave)
						.addComponent(lblstored))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblenterUID)
						.addComponent(lblNewLabel))
					.addGap(32))
		);
		setLayout(groupLayout);

	}
}
