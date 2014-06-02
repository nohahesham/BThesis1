package gui;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import databaseConnection.DBConnection;
import exceptions.ConnectionFailedException;
import exceptions.InitializeException;

@SuppressWarnings("serial")
public class HealthRecords extends JPanel {

JTable HealthRecords;
DefaultTableModel model;
private JTable table;
	public HealthRecords() {
		
		try {
			String[] c = new String[] {
					"hid", "owner", "ciphertext","policy"
				};
			String[][] s = DBConnection.HealthRecords();
			table = new JTable(s,c);
		} catch (InitializeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConnectionFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		add(table);


	}
	
	


}
