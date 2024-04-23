package mapping;
import mapping.TagHandler;
import mapping.AssetHandler;
import java.util.ArrayList;

public class Tester {
	private SQLController sqlite = SQLController.getConnector();
	
	public void runData() { 
		DBinit db = new DBinit();
		db.dbInit();
	
		db.closeDB();
	}
	
	public static void main (String[] args) { 
		Tester tester = new Tester();
		tester.runData();
	}
	
}
