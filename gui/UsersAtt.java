package gui;

import javax.swing.JPanel;
import javax.swing.JTable;

import databaseConnection.DBConnection;
import exceptions.ConnectionFailedException;
import exceptions.InitializeException;

@SuppressWarnings("serial")
public class UsersAtt extends JPanel {
	private JTable table;
	
	
	public UsersAtt() {
		
		try {
			String[][] s = DBConnection.UhasA();
			String[] c = new String[] {
					"uid", "aid"
				};
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


