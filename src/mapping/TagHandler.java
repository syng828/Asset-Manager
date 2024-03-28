package mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagHandler {
	
	private SQLController sqlite;

	public TagHandler() {
		sqlite = SQLController.getConnector();
		
	}
	
	//
	public void addTag(Tag tag) { 
		
		System.out.println(sqlite.insertData(tag.getTableName(), tag.getFields(), tag.getInputString()));
	}

	//
	public Tag searchTag(String name, String table){
		
		ResultSet rs = sqlite.selectQuery(" * ", table, " name = '"+ name +"'");
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
}