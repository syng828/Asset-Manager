package mapping;

public interface Tag {

	abstract String getName();
	
	abstract String getTableName();
	
	abstract String getDescription();

	boolean equals(Tag o);
}