package mapping;

//Facade class for limited external access to SQLController including only initialization, opening, and
//Closing DB connection.
public class DBinit {
    private SQLController sqlite = SQLController.getConnector();
    
    public DBinit(){
    	
    }

    //If AssetTracker.db file does not exist, create the file and necessary tables, and connect to DB
    //otherwise, only open connection to DB
    public void dbInit() {
    	
    	if(!sqlite.dbExists()) {
    		System.out.println(sqlite.connectDB());
            System.out.println(sqlite.createTables("Categories",
            		"CategoryID INTEGER PRIMARY KEY, Name TEXT unique not null"));
            System.out.println(sqlite.createTables("Locations",
            		"LocationID INTEGER PRIMARY KEY, Name TEXT unique not null, Description TEXT"));  
            System.out.println(sqlite.createTables("Assets",
            		"AssetID INTEGER PRIMARY KEY, Name TEXT unique not null, CategoryID INT not null,"
            		+ " LocationID INT not null, PurchaseDate DATE, Description TEXT, PurchasedValue INT, WarrantyExpDate DATE"));
            System.out.println("Inserting categories and locations..");
            //initializes two categories, three locations, and 5 assets
            sqlite.insertData("Categories", "Name", "'CategoryOne'");
            sqlite.insertData("Categories", "Name", "'CategoryTwo'");
            sqlite.insertData("Locations", "Name", "'LocationOne'");
            sqlite.insertData("Locations", "Name, Description", "'LocationTwo', 'This is a description.'");
            sqlite.insertData("Locations", "Name, Description", "'LocationThree', 'Another description.'");
            sqlite.insertData("Assets", "Name, CategoryID, LocationID, WarrantyExpDate", "'ExpAssetOne', '1', '1', '2024-05-09'");
            sqlite.insertData("Assets", "Name, CategoryID, LocationID, WarrantyExpDate", "'ExpAssetTwo', '1', '2', '2024-05-11'");
            sqlite.insertData("Assets", "Name, CategoryID, LocationID, WarrantyExpDate", "'ExpAssetThree', '2', '3', '2024-05-05'");
            sqlite.insertData("Assets", "Name, CategoryID, LocationID, WarrantyExpDate", "'ValidAssetOne', '2', '2', '2024-05-30'");
            sqlite.insertData("Assets", "Name, CategoryID, LocationID, WarrantyExpDate", "'ValidAssetTwo', '1', '3', '2024-06-07'");
    	}
    	else {
    		System.out.println(sqlite.connectDB());
    	}
    	
    	TagHandler.initMap();
    	AssetHandler.initCache();
    	AssetHandler.importAssets();
      
    }
    
    //Closes DB connection
    public void closeDB() {
    	System.out.println(sqlite.closeConnection());
    }

}



