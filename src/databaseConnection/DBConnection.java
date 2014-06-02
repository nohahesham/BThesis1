package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import exceptions.ConnectionFailedException;
import exceptions.InitializeException;

public class DBConnection {
static Connection connection;
	

	public static void Connect() throws InitializeException {
		try {Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HRDB;integratedSecurity=true");}
	    
		catch (ClassNotFoundException e) {throw new InitializeException("JDBC driver not Found");} 
     	catch (SQLException e) {throw new InitializeException("Connection to DATABASE failed");}
	
	}
	
	
	
	public static String[] AllAttributes() throws  InitializeException, ConnectionFailedException{
	   
		Connect();
	    String	sql = "SELECT * " + "FROM  attributes " ;
	   
	try {PreparedStatement Query = connection.prepareStatement(sql);
	     ResultSet resultset=Query.executeQuery();
		LinkedList<String> Atts = new LinkedList<String>();
		
        while(resultset.next())
        	Atts.add(resultset.getString("attribute"));
        
        return LStoArr(Atts);}
	
	catch (SQLException e) {throw new ConnectionFailedException("Connection to DATABASE failed");}
	
	}
	
	public static void StoreHR (int OID , byte [] CTD , String policy ) throws InitializeException, ConnectionFailedException {
		Connect();
		try {  
			String sql = "INSERT INTO Health_records (owner, ciphertext_data ,policy )" +
			        "VALUES (?, ?, ?)";
			
			PreparedStatement Query = connection.prepareStatement(sql);
			Query.setString(3,policy);
			Query.setInt(1, OID);
			Query.setBytes(2, CTD);
			Query.executeUpdate();
			}
		
		catch (SQLException e) {throw new ConnectionFailedException("Connection to DATABASE failed");}
	
	}
	
	public static int UserId(String username , String password) throws InitializeException, ConnectionFailedException{
		Connect();
		try {  
			String sql = "Select uid From users where username = ? AND password = ?";
			
			PreparedStatement Query = connection.prepareStatement(sql);
			Query.setString(1, username);
			Query.setString(2, password);
			ResultSet resultset = Query.executeQuery();
			if(resultset.next())
			return resultset.getInt("uid");
			else
				return -1;
			}
		
		catch (SQLException e) {
		
			throw new ConnectionFailedException("Connection to DATABASE failed");}
	
	}

	public static String[] UserAtt( int uid) throws InitializeException, ConnectionFailedException{
		Connect();
		try {  
String sql = "SELECT a.Attribute " + "FROM Users_has_Attributtes uha , attributes a " + " where uha.uid = ? AND uha.aid=a.aid";	
			PreparedStatement Query = connection.prepareStatement(sql);
			Query.setInt(1, uid);
			ResultSet resultset = Query.executeQuery();
			 LinkedList<String> atts = new LinkedList<String>();
			 while(resultset.next())
				 atts.add(resultset.getString("Attribute"));
			 return LStoArr(atts);
			 
	
			}
		
		catch (SQLException e) {
		
			throw new ConnectionFailedException("Connection to DATABASE failed");}
	
	}
		
	public static byte [] RetriveHR(int HRID) throws ConnectionFailedException, InitializeException{
		Connect();
		try {
			String sql = "SELECT * " + "FROM Health_records" +" where hid=?";
			
			PreparedStatement Query = connection.prepareStatement(sql);
			Query.setInt(1, HRID);
			ResultSet resultset = Query.executeQuery();
		 resultset.next();
		 byte[]  CTD = resultset.getBytes("ciphertext_data");
		 
		 return CTD;
		 
		}
		
		catch (SQLException e) {throw new ConnectionFailedException("Connection to DATABASE failed");}
	}
	
	private static String[] LStoArr(LinkedList<String> linkedList){
		
		String[] Arr= new String[linkedList.size()];
	
		for(int i=0;i<linkedList.size();i++){
		Arr[i]=(String) linkedList.get(i);
	}
		return
				Arr;
	}
	
	public static void main (String []args) throws InitializeException, ConnectionFailedException{
}
}
