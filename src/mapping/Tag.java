package mapping;

public interface Tag {

	//returns name of tag
	abstract String getName();
	//returns storage table name
	abstract String getTableName();
	//returns table data fields for SQL query 
	abstract String getFields();
	//concatenates and returns appropriate attributes as string for SQL query
	abstract String getInputString();
	//equals overload
	boolean equals(Tag o);
}