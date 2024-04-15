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
			return "Error adding tag";
		}
		return "Successfully added new Tag";
	}

}
