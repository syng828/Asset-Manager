package mapping;

public class DBinit {
    private SQLController sqlite = new SQLController();

    public void dbInit() {
    	
    	if(!sqlite.dbExists()) {
    		sqlite.connectDB();
            sqlite.createTables("Categories",
            		"CategoryID INT PRIMARY KEY unique not null AUTO INCREMENT, Name TEXT unique not null");
            sqlite.createTables("Locations",
            		"LocationID INT PRIMARY KEY unique not null AUTO INCREMENT, Name TEXT unique not null, Description TEXT");
            
            sqlite.closeConnection();
    	}
        
    }

}



