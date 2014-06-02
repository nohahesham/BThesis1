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

public static Connection connection;

	public static void Connect() throws InitializeException {
		try {Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=HRDB;integratedSecurity=true");}
	    
		catch (ClassNotFoundException e) {throw new InitializeException("JDBC driver not Found");} 
     	catch (SQLException e) {throw new InitializeException("Connection to DATABASE failed");}
	
	}
    private static String[][] LStoArr(LinkedList<String[]> linkedList){
		
		String[][] Arr= new String[linkedList.size()][3];
	
		for(int i=0;i<linkedList.size();i++){
		Arr[i]=(String[]) linkedList.get(i);
	}
		return
				Arr;
	}	
	public static String[][] Users() throws InitializeException, ConnectionFailedException{
		Connect();
		try {  
			String sql = "Select * From Users ";
			
			PreparedStatement Query = connection.prepareStatement(sql);
			ResultSet resultset = Query.executeQuery();
			LinkedList<String[]> users = new LinkedList<String[]>();
			
			while(resultset.next()){
			String [] U = new String[3]; 
			U[1]=resultset.getString("username");
			U[2]=resultset.getString("password");
			U[0]=Integer.toString(resultset.getInt("uid"));
			users.add(U);
			}
			return LStoArr(users);
			
			}
		
		catch (SQLException e) {
		
			throw new ConnectionFailedException("Connection to DATABASE failed");}
	
	}
	public static String[][] HealthRecords() throws InitializeException, ConnectionFailedException{
		Connect();
		try {  
			String sql = "Select * From Health_records ";
			
			PreparedStatement Query = connection.prepareStatement(sql);
			ResultSet resultset = Query.executeQuery();
			LinkedList<String[]> users = new LinkedList<String[]>();
			
			while(resultset.next()){
			String [] U = new String[4]; 
			U[3]=resultset.getString("policy");
			
			byte[] D = resultset.getBytes("ciphertext_data");
			
			U[2]=new String(D);
			
			U[0]=Integer.toString(resultset.getInt("Hid"));
			U[1]=Integer.toString(resultset.getInt("owner"));
			users.add(U);
			}
			return LStoArr(users);
			
			}
		
		catch (SQLException e) {
		
			throw new ConnectionFailedException("Connection to DATABASE failed");}
	
	}
	public static String[][] UhasA() throws InitializeException, ConnectionFailedException{
		Connect();
		try {  
			String sql = "Select * From Users_has_Attributtes ";
			
			PreparedStatement Query = connection.prepareStatement(sql);
			ResultSet resultset = Query.executeQuery();
			LinkedList<String[]> UhasA = new LinkedList<String[]>();
			
			while(resultset.next()){
			String [] U = new String[2]; 
			U[0]=Integer.toString(resultset.getInt("uid"));
			U[1]=Integer.toString(resultset.getInt("aid"));
			UhasA.add(U);
			}
			return LStoArr(UhasA);
			
			}
		
		catch (SQLException e) {
		
			throw new ConnectionFailedException("Connection to DATABASE failed");}
	
	}
	public static void Adduser(String username , String password) throws ConnectionFailedException, InitializeException{
		try{
			Connect();
			String sql = "INSERT INTO Users (username, password )" +
		        "VALUES (?, ?)";
		
		PreparedStatement Query = connection.prepareStatement(sql);
		Query.setString(1, username);
		Query.setString(2, password);
		Query.executeUpdate();
	
			
		}
	
	catch (SQLException e) {
	
		throw new ConnectionFailedException("Connection to DATABASE failed");}
	}
	public static void AddAttribute(String attribute) throws ConnectionFailedException, InitializeException{
		Connect();
		try{
			String sql = "INSERT INTO attributes (attribute)" +
		        "VALUES (?)";
		
		PreparedStatement Query = connection.prepareStatement(sql);
		Query.setString(1,attribute);
		Query.executeUpdate();
	
			
		}
	
	catch (SQLException e) {
	
		throw new ConnectionFailedException("Connection to DATABASE failed");}
	}
	public static void AtoU(int uid , int aid) throws ConnectionFailedException, InitializeException{
		try{
			Connect();
			String sql = "INSERT INTO Users_has_Attributtes (uid,aid)" +
		        "VALUES (?,?)";
		
		PreparedStatement Query = connection.prepareStatement(sql);
		Query.setInt(1,uid);
		Query.setInt(2,aid);
		Query.executeUpdate();
	
			
		}
	
	catch (SQLException e) {
	
		throw new ConnectionFailedException("Connection to DATABASE failed");}
	}
	public static String[][] Attributes() throws InitializeException, ConnectionFailedException{
		Connect();
		try {  
			String sql = "Select * From attributes ";
			
			PreparedStatement Query = connection.prepareStatement(sql);
			ResultSet resultset = Query.executeQuery();
			LinkedList<String[]> Attributes = new LinkedList<String[]>();
			
			while(resultset.next()){
			String [] U = new String[2]; 
			U[1]=resultset.getString("attribute");
			U[0]=Integer.toString(resultset.getInt("aid"));
			Attributes.add(U);
			}
			return LStoArr(Attributes);
			
			}
		
		catch (SQLException e) {
		
			throw new ConnectionFailedException("Connection to DATABASE failed");}
	
	}
	
	public static void main (String []args) throws InitializeException, ConnectionFailedException{
	
	
	}
	
	
	
}
