package mapping;

import java.sql.Date;

public class Asset {
	final static private String tableName = "Assets";
	final static private String fields = "Name, CategoryID, LocationID, PurchaseDate,"
			+ " Description, PurchasedValue, WarrantyExpDate";
	
	private String name;  
	private int categoryID;
	private int locationID;
	private Date purchaseDate;
	private String description;
	private int purchasedValue;
	private Date warrantyExpDate;
	
	public Asset(String name, int categoryID, int locationID) { 
		this.name = name;
		this.categoryID = categoryID;
		this.locationID = locationID;
		
		this.purchaseDate = null;
		this.description = ""; 
		this.purchasedValue = 0;
		this.warrantyExpDate = null;
	}
	
	public void setName(String name) { 
		this.name = name;
	}
	
	public void setCategoryID(int categoryID) { 
		this.categoryID = categoryID;
	}
	
	public void setLocationID(int locationID) { 
		this.locationID = locationID;
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
	
	public int getCategoryID() {
		return categoryID;
	}
	public int getLocationID() {
		return locationID;
	}
	
	public String getCategoryName() { 
		return AssetHandler.getCategoryName(categoryID);
	}
	
	public String getLocationName() { 
		return AssetHandler.getLocationName(categoryID);
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
	
	public static String getTableName() {
		return tableName;
	}
	
	public static String getFields() {
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
		
		return "'"+ name + "', " + "'"+categoryID+"'" + ", " + "'"+locationID+"'" + 
				", " + "'"+purchaseString+"'" + ", " + "'"+description+"'" + ", " +  "'"+purchasedValue+"'"
			+ ", " + "'"+warrantyString+"'";
	}
}