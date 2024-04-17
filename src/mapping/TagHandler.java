package mapping;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

//import java.sql.ResultSet; Imports planned for later version usage
//import java.sql.SQLException;

//Facade class that limits external layers' access to SQLController API
public class TagHandler {
	
	private static SQLController sqlite = SQLController.getConnector(); 
	private static HashMap<String, ArrayList<Tag>> map = new HashMap<>();
	
	public TagHandler() {
	}
	
	//Adds tag using SQLController.insertData. Returns Status feedback string from the method call
	public String addTag(Tag tag) { 
		
		String result = (sqlite.insertData(tag.getTableName(), tag.getFields(), tag.getInputString()));
		
		return result;
	}
	
	public static ArrayList<Category> getTagsToList(String tableName, String value, String field, String condition) { 
		try { 
			ResultSet rs = sqlite.selectData(tableName, value, field, condition);
			while (rs.next()) { 
				 rs.getString("")
			}
		}
		except { 
			
		}
		
	}
	
	public static ArrayList<Category> getCategoryList() { 
		sqlite.
	}
	private static ArrayList<Location> getLocationList() { 
		
	}
}