package mapping;

public class Location implements Tag {
	
	final private String tableName = "Locations";
	
	private String name;
	private String description;
	
	public Location(String name) {
		this.name = name;
		this.description = "";
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
