package mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Date;

public class AssetHandler {
	private static SQLController sqlite = SQLController.getConnector();
	private static Asset selectedAsset;
	
	private static HashMap<String, HashMap<String, Asset>> AssetMap = new HashMap <>();

	public AssetHandler() {
	}
	
	public String addAsset(Asset asset) { 
		
		String result = (sqlite.insertData(Asset.getTableName(), Asset.getFields(), asset.getInputString()));
		if(result.equals("Successfully added entry into Assets")) {
			AssetMap.get("All Assets").put(asset.getName(), asset);
			if(isExpired(asset.getWarrantyExpDate())){
				AssetMap.get("Expired Assets").put(asset.getName(),(asset));
			}
		}
		return result;
	}
	
	public static void initCache() {
		AssetMap.put("All Assets", new HashMap<String, Asset>());
		AssetMap.put("Expired Assets", new HashMap<String, Asset>());
	}
	
	public static void importAssets() {
		try {
			ResultSet rs = sqlite.selectAllData("Assets");
			while (rs.next()) {
				String purchDate = rs.getString("PurchaseDate");
				String warrantyDate = rs.getString("WarrantyExpDate");
				
				Asset asset = new Asset(rs.getString("Name"), rs.getInt("CategoryID"), rs.getInt("LocationID"));
				if(purchDate!= null && !purchDate.equals("NULL"))
					asset.setPurchaseDate(Date.valueOf(purchDate));
				asset.setDescription(rs.getString("Description"));
				asset.setPurchasedValue(rs.getInt("PurchasedValue"));
				if(warrantyDate!= null && !warrantyDate.equals("NULL"))
					asset.setWarrantyExpDate(Date.valueOf(warrantyDate));
				AssetMap.get("All Assets").put(asset.getName(), asset);
				if(isExpired(asset.getWarrantyExpDate())){
					AssetMap.get("Expired Assets").put(asset.getName(), asset);
				}
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static boolean hasExpiredAssets() {
		if(AssetMap.get("Expired Assets").isEmpty()) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static boolean isExpired(Date date) {
		if(date == null) {
			return false;
		}
		else if(date.compareTo(new Date(System.currentTimeMillis())) < 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static HashMap<String, Asset> getAllAssets(){
		return AssetMap.get("All Assets");
	}
	
	public static HashMap<String, Asset> getExpiredAssets(){
		return AssetMap.get("Expired Assets");
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
			AssetMap = new HashMap<>();
			initCache();
			importAssets();
			
		} catch (SQLException e) { 
			throw e; 
		}
	}
	
	public static String deleteAsset(Asset asset) {
		String result = sqlite.deleteData(asset.getName());
		//System.out.println(result);
		if(result.equals("Asset successfully deleted"));{
			AssetMap = new HashMap<>();
			initCache();
			importAssets();
		}
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
