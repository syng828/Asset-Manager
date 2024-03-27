package mapping;

class Category implements Tag {
	
	final private String tableName = "Categories";
	
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
	public String getDescription() {
		
		return null;
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