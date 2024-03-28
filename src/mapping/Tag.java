package mapping;

public interface Tag {

	abstract String getName();
	
	abstract String getTableName();
	
	abstract String getFields();
	
	abstract String getInputString();

	boolean equals(Tag o);
}