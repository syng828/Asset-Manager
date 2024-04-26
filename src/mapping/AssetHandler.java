package mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class AssetHandler {
	private static SQLController sqlite = SQLController.getConnector();
	private static Asset selectedAsset;

	public AssetHandler() {
	}
	
	public String addAsset(Asset asset) { 
		
		String result = (sqlite.insertData(Asset.getTableName(), Asset.getFields(), asset.getInputString()));
		
		return result;
	}
	
	public static ArrayList<Asset> search(String sub) {
		try {
			ResultSet rs = sqlite.selectLike("Assets", "Name", sub);
			ArrayList<Asset> a = new ArrayList<>();
			while (rs.next()) { 
				Asset asset = new Asset(rs.getString("Name"), rs.getInt("CategoryID"), rs.getInt("LocationID"));
				a.add(asset);
				
				String purchaseDateString = rs.getString("PurchaseDate");
				if (purchaseDateString != null && !purchaseDateString.equals("NULL")) 
					asset.setPurchaseDate(Date.valueOf(purchaseDateString));
				String warrantyDateString = rs.getString("WarrantyExpDate"); 
				if (warrantyDateString != null && !warrantyDateString.equals("NULL")) 
					asset.setWarrantyExpDate(Date.valueOf(warrantyDateString));
				
				asset.setDescription(rs.getString("Description"));
				asset.setPurchasedValue(rs.getInt("PurchasedValue"));
			}
			
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static void selectAsset(Asset asset) {
		selectedAsset = asset;
	}
	
	public static Asset getSelectedAsset() { 
		return selectedAsset;
	}
	
	public static void cancelSelectedAsset() {
		selectedAsset = null; 
	}
	
	public void updateAsset(String field, String value, String name) throws SQLException  {
		try {
			sqlite.editData("Assets", field, value, "Name", "'"+name+"'");
		} catch (SQLException e) { 
			throw e; 
		}
	}
	
	public static String deleteAsset(Asset asset) {
		String result = sqlite.deleteData(asset.getName());
		return result;
	}
	
	public static String getCategoryName(int id) { 
		try { 
			ResultSet rs = sqlite.select("Categories", "Name", "CategoryID", String.valueOf(id));
			return rs.getString("Name");
		} catch (SQLException e) { 
			e.printStackTrace(); 
			return null;
		}
	}
	
	public static String getLocationName(int id) { 
		try { 
			ResultSet rs = sqlite.select("Locations", "Name", "LocationID", String.valueOf(id));
			return rs.getString("Name");
		} catch (SQLException e) { 
			e.printStackTrace(); 
			return null;
		}
	}
	
}
