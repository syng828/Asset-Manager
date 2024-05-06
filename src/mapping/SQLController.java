package mapping;

import java.io.File;
import java.sql.*;

public class SQLController {
	
	//Singleton pattern usage for SQLController to only have one instance handle SQLite connection per session
	private static SQLController control = new SQLController();
	private Connection conn;
	
	private SQLController() {
	}
	
	//Returns single static SQLController instance
	protected static SQLController getConnector() {
		return control;
	}
	
	//Checks if AssetTracker.db exists, return true if exists
	protected boolean dbExists() {
		File file = new File ("AssetTracker.db");
		
		if(file.exists()) {
			return true;
		}
		return false;
	}
	
	//Establishes connection to Sqlite DB, returns status feedback string
	protected String connectDB() {
		System.out.println("starting");
		try {
	         Class.forName("org.sqlite.JDBC");
	         conn = DriverManager.getConnection("jdbc:sqlite:AssetTracker.db");
	      	} 
		catch( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	         return "Error " + e;
	      }
	    return"Opened database successfully";
	}
	
	//Closes connection to Sqlite DB, returns status feedback string
	protected String closeConnection() {
		
		try {
			conn.close();
		}
		catch(SQLException e) {
			return "SQL connection error " + e;
		}
		
		return "Connection Closed Successfully";
		
	}
	
	//Executes 'CREATE TABLE' SQL command based String parameters as fragments, 
	//returns status feedback string
	protected String createTables(String tableName, String fields) {
		
		try {
			Statement command = conn.createStatement();
			String query = "CREATE TABLE "+
							tableName+" ("+
							fields+")";
			command.executeUpdate(query);
			command.close();
		}
		catch(SQLException e) {
			return "SQL connection error " + e;
		}
		
		return "Tables successfully created";
		
	}
	
	protected String deleteData(String sub) {
		try {
			Statement command = conn.createStatement();
			//String query = "SELECT * FROM Assets WHERE name LIKE sub ";
			String query = "DELETE FROM Assets WHERE name='" + sub + "';";
			System.out.println(query);
			command.executeQuery(query);
			command.close();
		}
		catch(SQLException e) { 
			return "SQL connection error " + e;
		}
		
		return "Asset successfully deleted";
	}
	
	//Executes 'INSERT' SQL query based on string parameters as fragments, returns status feedback string
	protected String insertData(String tableName, String schema, String values ) {
		try {
			System.out.println("attempting insertion");
			Statement command = conn.createStatement();
			String query = "INSERT INTO " +
							tableName +
							" (" + schema + ") " +
							"VALUES (" +
							values+");";
			System.out.println(query);
			command.executeUpdate(query);
			command.close();
		}
		catch(SQLException e) {
			if (e.getMessage().contains("is not unique")) {
	            return "Duplicate entry in " + tableName;
	        } else {
	            return "Error inserting into " + tableName;
	        }
		}
		return "Successfully added entry into " + tableName;
	}
	
	//Executes 'SELECT' SQL query with all the data
	protected ResultSet selectAllData(String tableName)  { 
		try { 
			System.out.println("attempting to select data"); 
			Statement command = conn.createStatement();
			String query = "SELECT * FROM " + 
							tableName;  
			System.out.println(query);
			ResultSet rs = command.executeQuery(query);
			return rs;
		}
		catch(SQLException e) { 
			System.out.println(e);
			return null;
		}
	}
	//Executes "SELECT" SQL Query based on matching substring 
	protected ResultSet selectLike(String tableName, String matchField, String sub) {
		try {
			System.out.println("Fetching data matching with " + sub);
			Statement command = conn.createStatement();
			String query = "SELECT * FROM " + tableName + " WHERE " + matchField + " LIKE '%" +
		               sub+ "%';";
			ResultSet rs = command.executeQuery(query);
			return rs;
		}
		catch(SQLException e) { 
			System.out.println(e);
			return null;
		}
	}
	
	//Executes "SELECT" query, returns result based on given fields
	protected ResultSet select(String tableName, String fields, String matchField, String matchValue) { 
		try {
			Statement command = conn.createStatement();
			String query = "SELECT " + fields  + " FROM " + tableName + " WHERE " + matchField + "=" +
		               matchValue + ";";
			System.out.println(query); 
			ResultSet rs = command.executeQuery(query);
			return rs;
		}
		catch(SQLException e) { 
			System.out.println(e);
			return null;
		}
	}
	
	//Executes "SELECT" query with < > operators, returns result based on given fields
		protected ResultSet selectComparator(String tableName,  String matchField, String comparator, String matchValue) { 
			try {
				Statement command = conn.createStatement();
				String query = "SELECT * FROM " + tableName + " WHERE " + matchField + comparator +
			               matchValue + ";";
				System.out.println(query); 
				ResultSet rs = command.executeQuery(query);
				return rs;
			}
			catch(SQLException e) { 
				System.out.println(e);
				return null;
			}
		}
	
	//Executes "UPDATE" query based on giving parameters
	protected void editData(String tableName, String field, String value, String matchField, String matchValue) throws SQLException{
		try { 
			Statement command = conn.createStatement(); 
			String query = "UPDATE " + tableName + " SET " + field + 
					" = " + value + " WHERE " + matchField + " = " + matchValue + ";"; 
			command.executeUpdate(query);
			command.close(); 
		}
		catch (SQLException e) { 
			System.out.println(e);
			throw e;
		}
	}
}