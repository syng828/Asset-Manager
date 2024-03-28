package mapping;

//@TODO: Remove this file later, but use it for testing the backend
public class Tester {
	public static void main( String args[] ) {
		
		DBinit init = new DBinit();
		init.dbInit();
		
		Location cat = new Location("Newest");
		TagHandler tagger = new TagHandler();
		
		tagger.addTag(cat);
		init.closeDB();

		
	}
}
