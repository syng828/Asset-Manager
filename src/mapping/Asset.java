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
	
	public void setName(String name) { 
		this.name = name;
	}
	
	public void setCategory(String category) { 
		this.category = category;
	}
	
	public void setLocation(String location) { 
		this.location = location;
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
	
	public String getCategory() {
		return category;
	}
	
	public String getLocation() {
		return location;
	}
	
	public Date getPurchaseDate() { 
		return purchaseDate; 
	}
	
	public String getDescription() { 
		return description;
	}
	
	public int getPurchasedValue() { 
		return purchasedValue;
	}
	
	public Date getWarrantyExpDate() { 
		return warrantyExpDate; 
	}
	
	public String getTableName() {
		return tableName;
	}
	
	public String getFields() {
		return fields;
	}
	
	public String getInputString() {
		String purchaseString; 
		String warrantyString;
		if (purchaseDate == null) 
			purchaseString = "NULL";
		else
			purchaseString = purchaseDate.toString();
		System.out.println(purchaseString);
		
		if (warrantyExpDate == null) 
			warrantyString = "NULL";
		else
			warrantyString = warrantyExpDate.toString();
		System.out.println(warrantyString);
		
		return "'"+ name + "', " + "'"+category+"'" + ", " + "'"+location+"'" + 
				", " + "'"+purchaseString+"'" + ", " + "'"+description+"'" + ", " +  "'"+purchasedValue+"'"
			+ ", " + "'"+warrantyString+"'";
	}
}