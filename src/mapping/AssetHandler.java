package mapping;

public class AssetHandler {
	private SQLController sqlite;

	public AssetHandler() {
		sqlite = SQLController.getConnector();
	}
	
	public String addAsset(Asset asset) { 
		
		String result = (sqlite.insertData(asset.getTableName(), asset.getFields(), asset.getInputString()));
		
		return result;
	}
}
