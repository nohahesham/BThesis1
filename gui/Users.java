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

public class Users extends JPanel {
	private JTextField username;
	private JTextField password;
	private JTable table;
	private JLabel lblenterUserName;
	private JLabel lblNewLabel;
	private JLabel lblstored;

	/**
	 * Create the panel.
	 */
	public Users() {
		
		JLabel lblUsername = new JLabel("Username");
		
		username = new JTextField();
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("password");
		
		password = new JTextField();
		password.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(username.getText().length()==0 || password.getText().length()==0){
				if(username.getText().length()==0 )
					lblenterUserName.setVisible(true);
				if(password.getText().length()==0)
					lblNewLabel.setVisible(true);
					
					
			}
			else{
				try {

					DBConnection.Adduser(username.getText(), password.getText());
					lblstored.setVisible(true);
					String[][] s = DBConnection.Users();
					String[] c = new String[] {
							"ID", "username","password"
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
	    String[][] s = DBConnection.Users();
		String[] c = new String[] {
				"uid", "username","password"
			};
		
		table = new JTable(s,c);
		TableModel dataModel = new DefaultTableModel(s,c);
		table.setModel(dataModel );
		}
		catch(Exception e){
		e.printStackTrace();	
		}
		
		lblenterUserName = new JLabel("\"Enter User name\"");
		lblenterUserName.setForeground(Color.RED);
		lblenterUserName.setVisible(false);
		
		lblNewLabel = new JLabel("\"Enter password\"");
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
							.addComponent(lblUsername)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblPassword))
								.addComponent(lblenterUserName))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(lblUsername)
						.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave)
						.addComponent(lblstored))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblenterUserName)
						.addComponent(lblNewLabel))
					.addGap(32))
		);
		setLayout(groupLayout);

	}
}
