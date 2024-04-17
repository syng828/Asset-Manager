package mapping;

import java.util.Date;

public class Asset {
	final private String tableName = "Assets";
	final private String fields = "Name, Category, Location, PurchaseDate,"
			+ " Description, PurchasedValue, WarrantyExpirationDate";
	
	private String name; 
	private String category; 
	private String location;
	private Date purchaseDate;
	private String description;
	private float purchasedValue;
	private Date warrantyExpirationDate;
	
	public Asset(String name, String category, String location) { 
		this.name = name;
		this.category = category;
		this.location = location; 
		
		this.purchaseDate = new Date(0);
		this.description = ""; 
		this.purchasedValue = 0;
		this.warrantyExpirationDate = new Date(0); 
	}
	public void setPurchaseDate(Date purchaseDate) { 
		this.purchaseDate = purchaseDate; 
	}
	public void setDescription(String description) { 
		this.description = description; 
	}
	public void setPurchasedValue (float purchasedValue) { 
		this.purchasedValue = purchasedValue; 
	}
	public void setWarrantyExpirationDate(Date warrantyExpirationDate) { 
		this.warrantyExpirationDate = warrantyExpirationDate; 
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
}