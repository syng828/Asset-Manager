package mapping;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagHandler {
	
	private SQLController sqlite;

	public TagHandler() {
		sqlite = new SQLController();
		sqlite.connectDB();
		
	}
	
	//
	public void addTag(Tag tag) { 
		
		String table = tag.getTableName();
		if (table.equals("Categories"))
			sqlite.insertData(table, "", tag.getName());
		else if (table.equals("Locations"))
			sqlite.insertData(table, "", tag.getName() + ", " + tag.getDescription());
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