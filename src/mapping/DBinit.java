package mapping;

public class DBinit {
    private SQLController sqlite = SQLController.getConnector();

    public void dbInit() {
    	
    	if(!sqlite.dbExists()) {
    		sqlite.connectDB();
            sqlite.createTables("Categories",
            		"CategoryID INT PRIMARY KEY unique not null AUTO INCREMENT, Name TEXT unique not null");
            sqlite.createTables("Locations",
            		"LocationID INT PRIMARY KEY unique not null AUTO INCREMENT, Name TEXT unique not null, Description TEXT");  
            
    	}
      
    }
    
    public void closeDB() {
    	sqlite.closeConnection();
    }
    
    public static void main (String [] args) { 
    	DBinit db = new DBinit(); 
    	db.dbInit();
    	System.out.println("success");
    }

}



