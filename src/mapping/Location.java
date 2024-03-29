package mapping;

//Basic class to store data on single location
public class Location implements Tag {
	
	final private String tableName = "Locations";
	final private String fields = "Name, Description";
	
	private String name;
	private String description;
	
	public Location(String name) {
		this.name = name;
		this.description = "None";
	}
	public Location(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public String getDescription() { 
		return description; 
	}
	public String getFields() {
		return fields;
	}
	public String getInputString() {
		
		return "'"+getName() + "', " + "'"+getDescription()+"'";
	}
	
	@Override
	public boolean equals(Tag o) {
		if(o.getName() == this.name) {
			return true;
		}
		else {
			return false;
		}
	}

}
