package mapping;

import java.io.File;
import java.sql.*;

public class SQLController {

	private static SQLController control = new SQLController();
	private Connection conn;
	
	private SQLController() {
	}
	
	protected static SQLController getConnector() {
		return control;
	}
	
	protected boolean dbExists() {
		File file = new File ("AssetTracker.db");
		
		if(file.exists()) {
			return true;
		}
		return false;
	}
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
	
	protected String closeConnection() {
		
		try {
			conn.close();
		}
		catch(SQLException e) {
			return "SQL connection error " + e;
		}
		
		return "Connection Closed Successfully";
		
	}
	
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
			return "Error adding tag";
		}
		return "Successfully added new Tag";
	}
	
	protected ResultSet selectQuery(String fields, String tableName, String condition) {
		try {
			System.out.println("Attempting selection query");
			Statement command = conn.createStatement();
			String query = "SELECT " + fields + " FROM " + tableName;
			
			if(!condition.equals("")) {
				query+=" WHERE "+condition;
			}
			
			query+= ";";
			
			System.out.println(query);
			ResultSet rs = command.executeQuery(query);
			command.close();
			return rs;
		}
		catch(SQLException e) {
			System.out.println("SQL connection error " + e);
			return null;
		}
		
	}

}
