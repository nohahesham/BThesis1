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

public class Attributes extends JPanel {
	private JTextField att;
	private JTable table;
	private JLabel lblenterUserName;
	private JLabel lblstored;

	/**
	 * Create the panel.
	 */
	public Attributes() {
		
		JLabel lblUsername = new JLabel("Attribute");
		
		att = new JTextField();
		att.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(att.getText().length()==0 ){
				
					
					
			}
			else{
				try {

					DBConnection.AddAttribute(att.getText());
					lblstored.setVisible(true);
					String[][] s = DBConnection.Attributes();
					String[] c = new String[] {
							"aid", "attribute"
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
	    String[][] s = DBConnection.Attributes();
		String[] c = new String[] {
				"aid","Attribute"
			};
		
		table = new JTable(s,c);
		TableModel dataModel = new DefaultTableModel(s,c);
		table.setModel(dataModel );
		}
		catch(Exception e){
		e.printStackTrace();	
		}
		
		
		lblenterUserName = new JLabel("\"Enter attribute \"");
		lblenterUserName.setForeground(Color.RED);
		lblenterUserName.setVisible(false);
		
		
		lblstored = new JLabel("\"Stored\"");
		lblstored.setVisible(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblUsername)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(att, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblenterUserName))
							.addGap(248)
							.addComponent(btnSave)
							.addGap(18)
							.addComponent(lblstored)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(7)
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsername)
						.addComponent(att, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave)
						.addComponent(lblstored))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblenterUserName)
					.addGap(32))
		);
		setLayout(groupLayout);

	}
}
