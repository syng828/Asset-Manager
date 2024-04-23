package mapping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssetHandler {
	private static SQLController sqlite = SQLController.getConnector();

	public AssetHandler() {
	}
	
	public String addAsset(Asset asset) { 
		
		String result = (sqlite.insertData(asset.getTableName(), asset.getFields(), asset.getInputString()));
		
		return result;
	}
	
	public static ArrayList<Asset> getAssetList() { 
		try { 
			ResultSet rs = sqlite.selectAllData("Assets"); 
			ArrayList<Asset> assetArray = new ArrayList<>();
			
			while (rs.next()) { 
				Asset assets = new Asset(rs.getString("Name"), rs.getString("Category"), rs.getString("Location"));
				assetArray.add(assets);
			}
			return assetArray;
		}
		catch (SQLException e){ 
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<Asset> search(String sub) {
		// TODO Auto-generated method stub
		
		try {
			ResultSet rs = sqlite.select(sub);
			ArrayList<Asset> a = new ArrayList<>();
			while (rs.next()) { 
				Asset assets = new Asset(rs.getString("Name"), rs.getString("Category"), rs.getString("Location"));
				a.add(assets);
			}
			return a;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static String deleteAsset(Asset asset) {
		String result = sqlite.deleteData(asset.getName());
		return result;
	}
	
}
