package mapping;
import mapping.TagHandler;
import mapping.AssetHandler;
import java.util.ArrayList;

public class Tester {
	
	public static void main (String[] args) { 
		DBinit db = new DBinit();
		db.dbInit();
		
		ArrayList<String> categories = TagHandler.getCategoryList();
				
		for (String c : categories) { 
			System.out.println(c);
		}
		//adding
		/* Asset asset = new Asset("Asset", "CategoryOne", "LocationOne");
		ArrayList <Asset> assets = AssetHandler.getAssetList();
		for (Asset a : assets) { 
			System.out.println(a.getInputString());
		} */
		//search
		
		//editing
		
		//deleting 
		
		db.closeDB();
	}
	
}
