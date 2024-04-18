package mapping;

import java.sql.Date;

public class Asset {
	final private String tableName = "Assets";
	final private String fields = "Name, Category, Location, PurchaseDate,"
			+ " Description, PurchasedValue, WarrantyExpDate";
	
	private String name;  
	private String category; 
	private String location;
	private Date purchaseDate;
	private String description;
	private int purchasedValue;
	private Date warrantyExpDate;
	
	public Asset(String name, String category, String location) { 
		this.name = name;
		this.category = category;
		this.location = location; 
		
		this.purchaseDate = null;
		this.description = ""; 
		this.purchasedValue = 0;
		this.warrantyExpDate = null;
	}
	public void setPurchaseDate(Date purchaseDate) { 
		this.purchaseDate = purchaseDate; 
	}
	public void setDescription(String description) { 
		this.description = description; 
	}
	public void setPurchasedValue (int purchasedValue) { 
		this.purchasedValue = purchasedValue; 
	}
	public void setWarrantyExpDate(Date warrantyExpDate) { 
		this.warrantyExpDate = warrantyExpDate; 
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
		return "'"+ name + "', " + "'"+category+"'" + ", " + "'"+location+"'" + 
				", " + "'"+purchaseDate+"'" + ", " + "'"+description+"'" + ", " +  "'"+purchasedValue+"'"
			+ ", " + "'"+warrantyExpDate+"'";
	}
}