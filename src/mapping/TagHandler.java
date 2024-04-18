package mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

//import java.sql.ResultSet; Imports planned for later version usage
//import java.sql.SQLException;

//Facade class that limits external layers' access to SQLController API
public class TagHandler {
	
	private static SQLController sqlite = SQLController.getConnector();
	private static HashMap<String, ArrayList<String>> tagMap = new HashMap<>();
	
	public TagHandler() {
	}
	
	//Adds tag using SQLController.insertData. Returns Status feedback string from the method call
	public String addTag(Tag tag) { 
		
		String result = (sqlite.insertData(tag.getTableName(), tag.getFields(), tag.getInputString()));
		
		return result;
	}
	
	//Adds category and locations into the map 
	public static void initMap() { 
		try { 
			ResultSet rsCat = sqlite.selectAllData("Categories");
			ArrayList<String> catArray = new ArrayList<>();
			
			while (rsCat.next()) { 
				 String name = rsCat.getString("Name");
				 catArray.add(name);
				 
			}
			tagMap.put("Categories", catArray); 
			
			ResultSet rsLoc = sqlite.selectAllData("Locations"); 
			ArrayList<String> locArray = new ArrayList<>();
			
			while (rsLoc.next()) { 
				
				locArray.add(rsLoc.getString("Name"));
			}
			tagMap.put("Locations", locArray);
		}
		catch (SQLException e){ 
			e.printStackTrace();
		}
	}
	public static ArrayList<String> getCategoryList() { 
		return tagMap.get("Categories");
	}
	
	public static ArrayList<String> getLocationList() { 
		return tagMap.get("Locations");
	}
	
	
}