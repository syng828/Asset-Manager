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
	private static HashMap<String, Integer> catMap = new HashMap<>();
	private static HashMap<String, Integer> locMap = new HashMap<>();
	
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
			
			while (rsCat.next()) { 
				 String name = rsCat.getString("Name");
				 int id = rsCat.getInt("CategoryID");
				 
				 catMap.put(name, id);
				 
			}
		
			ResultSet rsLoc = sqlite.selectAllData("Locations"); 
			
			while (rsLoc.next()) { 
				
				String name = rsLoc.getString("Name");
				int id = rsLoc.getInt("LocationID");
				locMap.put(name, id);
			}
			
		}
		catch (SQLException e){ 
			e.printStackTrace();
		}
	}
	public static HashMap<String, Integer> getCategoryMap() { 
		return catMap;
	}
	
	public static HashMap<String, Integer> getLocationMap() { 
		return locMap;
	}
	
	public static String getCategoryName(int id) { 
		try { 
			ResultSet rs = sqlite.select("Categories", "Name", "CategoryID", String.valueOf(id));
			return rs.getString("Name");
		} catch (SQLException e) { 
			e.printStackTrace(); 
			return null;
		}
	}
	
	public static String getLocationName(int id) { 
		try { 
			ResultSet rs = sqlite.select("Locations", "Name", "LocationID", String.valueOf(id));
			return rs.getString("Name");
		} catch (SQLException e) { 
			e.printStackTrace(); 
			return null;
		}
	}
	
}