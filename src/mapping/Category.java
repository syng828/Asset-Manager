package mapping;

class Category implements Tag {
	private String name;
	
	protected Category(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
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