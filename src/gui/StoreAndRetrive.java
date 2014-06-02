package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JButton;
import Main.EncDec;
import databaseConnection.DBConnection;
import exceptions.ConnectionFailedException;
import exceptions.InitializeException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class StoreAndRetrive {

	private JFrame frame;
	private JTextField username;
	private JTextField password;
	 static DefaultListModel listModel = new DefaultListModel();
	 DefaultComboBoxModel BoxModel = new DefaultComboBoxModel();
	 String RP="";
	 boolean postback;
	 private JTextField UN;
	 private JTextField PW;
	 private JTextField HRID;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreAndRetrive window = new StoreAndRetrive();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StoreAndRetrive() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private static void getAttributtes(){
		
		try {
		String []	Attributes = DBConnection.AllAttributes();
		
		for(int i=0 ; i < Attributes.length ; i++)
			listModel.addElement(Attributes[i]);
			
		} catch (InitializeException e) {
		
			e.printStackTrace();
		} catch (ConnectionFailedException e) {
			
			e.printStackTrace();
		}


	  }
				
	
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 930, 694);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel Store = new JPanel();
		
		
		JLabel lblOwnerId = new JLabel("Username");
		
		username = new JTextField();
		username.setColumns(10);
		
		password = new JTextField();
		password.setColumns(10);
		
		JLabel lblAuthorId = new JLabel("Password");
		
		JLabel lblAttributes = new JLabel("Attributes");
		
		final JTextPane HRD = new JTextPane();
		final JLabel policy = new JLabel("");
		policy.setVisible(false);
		
		final JLabel Con = new JLabel("Condition");
		Con.setVisible(false);
		
		final JLabel PO = new JLabel("Policy : ");
		final JList Att = new JList();
		final JButton ADD = new JButton("+");
	ADD.setVisible(false);
		final JComboBox Condiation = new JComboBox();
		Condiation.addActionListener(new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	policy.setText(RP);
		    	
		    	for(int i=0;i<Att.getSelectedValues().length;i++){
					if(i==0 && policy.getText().length()!=0){
						policy.setText(policy.getText() + " "+ (String)Att.getSelectedValues()[i]);
						continue;}
					
					policy.setText(policy.getText() + " " + (String)Att.getSelectedValues()[i]  );}
		    	
		    	if(Condiation.getSelectedItem()!=null)
		    	policy.setText(policy.getText() + " " + (String)Condiation.getSelectedItem() );
		    }});
		
		Condiation.setVisible(false);
		
			
        Att.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
			 
			//	if(postback){
			// postback=false;
				// return;
			// }
				
				policy.setText(RP);
			 
				if(Att.getSelectedValues().length==0)
					ADD.setVisible(false);
				else
					ADD.setVisible(true);
				
				int C = Att.getSelectedValues().length;
				
				if(RP.length()>2)
					C++;
				
				
				
				for(int i=0;i<Att.getSelectedValues().length;i++){
					if(i==0 && policy.getText().length()>2){
						policy.setText(policy.getText() + " "+ (String)Att.getSelectedValues()[i]);
						continue;}
					
					policy.setText(policy.getText() + " " + (String)Att.getSelectedValues()[i]  );}
				
				if(C>1){
				BoxModel.removeAllElements();	
				for(int i=C ; i>0 ;i--)
				BoxModel.addElement(i+"of" + C);
				
				
				Condiation.setModel(BoxModel);
				Condiation.setVisible(true);
				Con.setVisible(true);}
				
				else{
					Condiation.setVisible(false);
					Con.setVisible(false);	
				}
				 
				policy.setVisible(true);
				
	
				
				
			
        	
			}});
	
		
		

		
		JLabel lblNewLabel = new JLabel("Healt Record data");
		

		
		final JTextPane console = new JTextPane();
		console.setEnabled(false);
		console.setVisible(false);
		
		final JLabel lblenterUserName = new JLabel("\"Enter Username\"");
		lblenterUserName.setVisible(false);
		lblenterUserName.setForeground(Color.RED);
		
		final JLabel lblenterPassword = new JLabel("\"Enter Password\"");
		lblenterPassword.setVisible(false);
		lblenterPassword.setForeground(Color.RED);
		
		final JLabel lblenterData = new JLabel("\"enter data\"");
		lblenterData.setVisible(false);
		lblenterData.setForeground(Color.RED);
		final JLabel lblenterPolicy = new JLabel("\"Enter Policy\"");
		lblenterPolicy.setForeground(Color.RED);
		lblenterPolicy.setBackground(Color.WHITE);
		lblenterPolicy.setVisible(false);
		
		final JButton store = new JButton("Store");
		
	
		
		
		
		
		

							
							
					
		ADD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblenterPolicy.setVisible(false);
				if(policy.getText().length()>2){
				RP = policy.getText();
			    policy.setText(RP);
			    
			    listModel.removeAllElements();
			    
			    BoxModel.removeAllElements();
			    
			    Condiation.setVisible(false);
				
			    Con.setVisible(false);
			    
				StoreAndRetrive.getAttributtes();
					Att.setModel(listModel);}
			    
			    }
			
		});
		
	
		final JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setVisible(false);
		final ActionListener R = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				RP="";
				
				username.setText("");
				password.setText("");
				
				
				HRD.setText("");
				policy.setText("");
				
				listModel = new DefaultListModel();
				 BoxModel = new DefaultComboBoxModel();
				 
				StoreAndRetrive.getAttributtes();
					Att.setModel(listModel);
				
				username.setEditable(true);
				password.setEditable(true);
				Att.enable(true);
				Condiation.enable(true);
				HRD.setEditable(true);
				ADD.enable(true);
				console.setVisible(false);
				  
				        store.removeActionListener( store.getAction() );
				        
				    
				         btnRefresh.setVisible(false);
				         store.setVisible(true);
			}};
			
			
			
		ActionListener S = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(username.getText().length()==0 ||password.getText().length()==0 || HRD.getText().length()==0 || policy.getText().length()==0  ){
					if(username.getText().length()==0)
						lblenterUserName.setVisible(true);
					else
						lblenterUserName.setVisible(false);
					if(password.getText().length()==0)
						lblenterPassword.setVisible(true);
					else
						lblenterPassword.setVisible(false);
					if(HRD.getText().length()==0)
						lblenterData.setVisible(true);
					else
						lblenterData.setVisible(false);
					if(RP.length()<2)
						lblenterPolicy.setVisible(true);
					else
						lblenterPolicy.setVisible(true);
				}
					
				else{
					username.setEditable(false);
					password.setEditable(false);
					Att.enable(false);
					Condiation.enable(false);
					HRD.setEditable(false);
					ADD.enable(false);
					console.setVisible(true);
					
					console.setText("Starting the store process .. "+"\n"+"Checking username , password and retrivig user ID");
					
					try {
					
						int OID = DBConnection.UserId(username.getText(), password.getText());
						
						if(OID!=-1){
							
						    
						
						    console.setText(console.getText()+ "\n" +"Encrypting data before storing");
							
							for(int i=0 ;i<RP.length();i++){
								if(Character.isWhitespace(RP.charAt(i)))
								RP = RP.substring(i+1);
									else
										break;}
							
	               
							byte[] CTD = EncDec.Encrypt(RP, HRD.getText());
							
							
							console.setText(console.getText()+ "\n" +"Encryption completed");
							
							console.setText(console.getText()+ "\n" +"Storing encrypted file in the database");
						
							DBConnection.StoreHR(OID, CTD, RP);
							
							console.setText(console.getText()+ "\n" +"Storing encrypted file completed");
							  
							  store.setVisible(false);
							  btnRefresh.setVisible(true);
							  
						}
												else{
													console.setText(console.getText()+ "\n" +"Wrong username or password");
													username.setEditable(true);
													password.setEditable(true);}
						}
					
				   
					catch (InitializeException e1) {
								e1.printStackTrace();}
	                catch (ConnectionFailedException e1) {
								e1.printStackTrace();} 
					catch (Exception e1) {	
						        e1.printStackTrace();}
										}

			}};
		

	
			store.addActionListener(S);
		
	
		
		btnRefresh.addActionListener(R);
			
		GroupLayout gl_Store = new GroupLayout(Store);
		gl_Store.setHorizontalGroup(
			gl_Store.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_Store.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_Store.createParallelGroup(Alignment.LEADING)
						.addComponent(console, GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
						.addGroup(gl_Store.createSequentialGroup()
							.addComponent(HRD, GroupLayout.PREFERRED_SIZE, 644, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_Store.createParallelGroup(Alignment.LEADING)
								.addComponent(lblenterData)
								.addComponent(store, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)))
						.addGroup(gl_Store.createSequentialGroup()
							.addGroup(gl_Store.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_Store.createSequentialGroup()
									.addComponent(lblOwnerId)
									.addGap(18)
									.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblAttributes))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblenterUserName)
							.addGap(79)
							.addGroup(gl_Store.createParallelGroup(Alignment.LEADING)
								.addComponent(Con)
								.addGroup(gl_Store.createSequentialGroup()
									.addComponent(Condiation, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblAuthorId)
									.addGap(18)
									.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(gl_Store.createParallelGroup(Alignment.LEADING)
										.addComponent(btnRefresh)
										.addComponent(lblenterPassword)))))
						.addGroup(gl_Store.createSequentialGroup()
							.addComponent(Att, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_Store.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_Store.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_Store.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_Store.createSequentialGroup()
											.addComponent(PO)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(policy))
										.addComponent(lblenterPolicy)))
								.addGroup(gl_Store.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(ADD))))
						.addComponent(lblNewLabel))
					.addContainerGap())
		);
		gl_Store.setVerticalGroup(
			gl_Store.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_Store.createSequentialGroup()
					.addGap(35)
					.addGroup(gl_Store.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOwnerId)
						.addComponent(password, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAuthorId)
						.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblenterUserName)
						.addComponent(lblenterPassword))
					.addGap(47)
					.addGroup(gl_Store.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_Store.createSequentialGroup()
							.addGap(47)
							.addGroup(gl_Store.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAttributes)
								.addComponent(Con))
							.addGap(18)
							.addGroup(gl_Store.createParallelGroup(Alignment.BASELINE)
								.addComponent(Att, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
								.addComponent(Condiation, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(lblNewLabel))
						.addGroup(gl_Store.createSequentialGroup()
							.addGap(143)
							.addGroup(gl_Store.createParallelGroup(Alignment.BASELINE)
								.addComponent(ADD)
								.addComponent(btnRefresh))
							.addGap(45)
							.addGroup(gl_Store.createParallelGroup(Alignment.BASELINE)
								.addComponent(PO)
								.addComponent(policy))
							.addGap(19)
							.addComponent(lblenterPolicy)))
					.addGap(18)
					.addGroup(gl_Store.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_Store.createSequentialGroup()
							.addComponent(lblenterData)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(store, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addComponent(HRD, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(console, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		Store.setLayout(gl_Store);
		final JPanel Retrive = new JPanel();
		
	
		this.getAttributtes();
			Att.setModel(listModel);
			tabbedPane.addTab("Store", null, Store, null);
			
			tabbedPane.addTab("Retrive", null, Retrive, null);	
			
			JLabel lblUsername = new JLabel("Username");
			
			UN = new JTextField();
			UN.setColumns(10);
			
			JLabel lblPassword = new JLabel("Password");
			
			PW = new JTextField();
			PW.setColumns(10);
			
			JLabel lblHrId = new JLabel("Health Record ID");
			
			HRID = new JTextField();
			HRID.setColumns(10);
			
			JLabel lblHealthRecordData = new JLabel("Health Record data");
			
			final JTextPane HealthRecordData = new JTextPane();
			HealthRecordData.setEnabled(false);
			HealthRecordData.setEditable(false);
			
			final JTextPane Cons = new JTextPane();
			Cons.setVisible(false);
			Cons.setEditable(false);
			Cons.setEnabled(false);
			
			final JLabel lblenterUsername = new JLabel("\"Enter Username\"");
			lblenterUsername.setVisible(false);
			lblenterUsername.setForeground(Color.RED);
			
			final JLabel lblenterPassword_1 = new JLabel("\"Enter password\"");
			lblenterPassword_1.setForeground(Color.RED);
			lblenterPassword_1.setVisible(false);
			
			final JLabel lblenterValidHealth = new JLabel("\"Enter valid Health Record ID\"");
			lblenterValidHealth.setForeground(Color.RED);
			lblenterValidHealth.setVisible(false);
			
			final JButton RetriveB = new JButton("Retrive");
			
			
	
			
			final JButton btnRefresh_1 = new JButton("Refresh");
			btnRefresh_1.setVisible(false);
			
			 ActionListener RE =  new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					
					UN.setText("");
					PW.setText("");
					
					
					HRID.setText("");
					HealthRecordData.setText("");
					Cons.setText("");
					Cons.setVisible(false);
				
						
					UN.setEditable(true);
					PW.setEditable(true);
					HRID.setEditable(true);
					console.setVisible(false);
					btnRefresh_1.setVisible(false);
					RetriveB.setVisible(true);
					
				  
				}};
				
				 ActionListener RET =  new ActionListener(){

						public void actionPerformed(ActionEvent e) {
							
						
							boolean Int;
							try { 
							        Integer.parseInt(HRID.getText()); 
							        Int = true;
							    } catch(NumberFormatException e1) { 
							         Int=false;
							    }
							
							if(UN.getText().length()==0 ||PW.getText().length()==0 || HRID.getText().length()==0 || !Int  ){
								if(UN.getText().length()==0)
									lblenterUsername.setVisible(true);
								else
									lblenterUsername.setVisible(false);
								if(PW.getText().length()==0)
									lblenterPassword_1.setVisible(true);
								else
									lblenterPassword_1.setVisible(false);
								if(HRID.getText().length()==0)
									lblenterValidHealth.setVisible(true);
								else
									lblenterValidHealth.setVisible(false);
								if(!Int)
									lblenterValidHealth.setVisible(true);
								else
									lblenterValidHealth.setVisible(true);
							}
							else{
								UN.setEditable(false);
								PW.setEditable(false);
								HRID.setEditable(false);
								Cons.setVisible(true);
								
								Cons.setText("Starting the Retrive process .. "+" \n"+"Checking username , password and retrivig user ID");
								
								try {
									
								
									int OID = DBConnection.UserId(UN.getText(), PW.getText());
					
									if(OID!=-1){
										Cons.setText(Cons.getText() + "\n"+ "Retriving user attributes .. ");
								        String[] att = DBConnection.UserAtt(OID);
								        
								        
									    Cons.setText(Cons.getText()+ "\n" +"Retriving encrypted data from the database");
										
				               
										byte[] CTD = DBConnection.RetriveHR(Integer.parseInt(HRID.getText()));
										
										
										
										Cons.setText(Cons.getText()+ "\n" +"Retriving completed");
										
										
										Cons.setText(Cons.getText()+ "\n" +"decrypting  the data");
									
										String data = EncDec.Decrypt(CTD, att);
										
										Cons.setText(Cons.getText()+ "\n" +"process completed");
										
										HealthRecordData.setText(data);

										btnRefresh_1.setVisible(true);
										RetriveB.setVisible(false);
									
									}
									
									else{
										Cons.setText(Cons.getText()+ "\n" +"Wrong username or password");
										UN.setEditable(true);
										PW.setEditable(true);
										
									}
								
								} catch (InitializeException e1) {
									// TODO Auto-generated catch block
									Cons.setText(Cons.getText()+ "\n" +e1.getMessage());
								} catch (ConnectionFailedException e1) {
									// TODO Auto-generated catch block
									Cons.setText(Cons.getText()+ "\n" +e1.getMessage());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									Cons.setText(Cons.getText()+ "\n" +e1.getMessage());
								}
									
						
								
									
							}	
						
							
						  
						}};
						
						RetriveB.addActionListener(RET);
				
			btnRefresh_1.addActionListener(RE);

			
		
			GroupLayout gl_Retrive = new GroupLayout(Retrive);
			gl_Retrive.setHorizontalGroup(
				gl_Retrive.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_Retrive.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_Retrive.createParallelGroup(Alignment.LEADING)
							.addComponent(Cons, GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
							.addComponent(HealthRecordData, GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
							.addGroup(gl_Retrive.createSequentialGroup()
								.addComponent(lblHrId)
								.addGap(18)
								.addComponent(HRID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblenterValidHealth))
							.addGroup(gl_Retrive.createSequentialGroup()
								.addComponent(lblUsername)
								.addGap(18)
								.addComponent(UN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblenterUsername)
								.addGap(123)
								.addComponent(lblPassword)
								.addGap(18)
								.addComponent(PW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblenterPassword_1))
							.addGroup(gl_Retrive.createSequentialGroup()
								.addComponent(lblHealthRecordData)
								.addGap(644)
								.addGroup(gl_Retrive.createParallelGroup(Alignment.LEADING)
									.addComponent(btnRefresh_1)
									.addComponent(RetriveB))
								.addGap(17)))
						.addContainerGap())
			);
			gl_Retrive.setVerticalGroup(
				gl_Retrive.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_Retrive.createSequentialGroup()
						.addGap(24)
						.addGroup(gl_Retrive.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblUsername)
							.addComponent(UN, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(PW, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPassword)
							.addComponent(lblenterPassword_1)
							.addComponent(lblenterUsername))
						.addPreferredGap(ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
						.addGroup(gl_Retrive.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(gl_Retrive.createSequentialGroup()
								.addGroup(gl_Retrive.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblHrId)
									.addGroup(gl_Retrive.createParallelGroup(Alignment.BASELINE)
										.addComponent(HRID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblenterValidHealth)))
								.addGap(120)
								.addComponent(lblHealthRecordData)
								.addGap(18))
							.addGroup(gl_Retrive.createSequentialGroup()
								.addComponent(btnRefresh_1)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(RetriveB)
								.addGap(33)))
						.addComponent(HealthRecordData, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(80)
						.addComponent(Cons, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
						.addContainerGap())
			);
			Retrive.setLayout(gl_Retrive);
		
		
	}
}
