package mapping;

//import java.sql.ResultSet; Imports planned for later version usage
//import java.sql.SQLException;

//Facade class that limits external layers' access to SQLController API
public class TagHandler {
	
	private SQLController sqlite;

	public TagHandler() {
		sqlite = SQLController.getConnector();
		
	}
	
	//Adds tag using SQLController.insertData. Returns Status feedback string from the method call
	public String addTag(Tag tag) { 
		
		String result = (sqlite.insertData(tag.getTableName(), tag.getFields(), tag.getInputString()));
		
		return result;
	}

	/* PROTOTYPE search single Tag by name and type. Still under development and testing DO NOT USE
	public Tag searchTag(String name, String table){
		
		ResultSet rs = sqlite.selectQuery(" * ", table, "name = '"+ name +"'");
		Tag tag = null;
		
		try {
			
			if(rs.next()) {
				if (table.equals("Categories")) {
					tag = new Category(rs.getString("Name"));
				}	
				else if (table.equals("Locations")) {
					tag = new Location(rs.getString("Name"), rs.getString("Description"));
				}
			}
			
			rs.close();
		}
		catch(SQLException e) {
			
		}
		return tag;
	}
	*/
}