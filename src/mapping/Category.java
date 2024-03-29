package mapping;

//Basic class to store data for single category
public class Category implements Tag {
	
	final private String tableName = "Categories";
	final private String fields = "Name";
	
	private String name;
	
	
	public Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTableName() {
		return tableName;
	}
	public String getFields() {
		return fields;
	}
	public String getInputString() {
		return "'" + getName() + "'";
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