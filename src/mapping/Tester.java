package mapping;

import java.util.*;

public class Tester {

	public static void main(String[] args) {
		SQLController sqlite = SQLController.getConnector();
		
		sqlite.connectDB();
		TagHandler.initMap();
    	AssetHandler.initCache();
    	AssetHandler.importAssets();
    	
    	HashMap<String, Asset> all = AssetHandler.getAllAssets();
    	HashMap<String, Asset> exp = AssetHandler.getExpiredAssets();
    	
    	
    	System.out.println("\nall\n");
    	for(String asset: all.keySet()) {
    		System.out.println(asset);
    	}
    	System.out.println("\nexp\n");
    	for(String asset: exp.keySet()) {
    		System.out.println(asset);
    	}
    	
    	AssetHandler.deleteAsset(all.get("Expired"));
    	
    	System.out.println("\nall\n");
    	for(String asset: all.keySet()) {
    		System.out.println(asset);
    	}
    	
    	System.out.println(all.get("notExpired").getInputString());
    	
    	
	}
	
	
}
