package mapping;
import java.util.*;

public class TagHandler {
	
	public static HashSet<Tag> categorySet = new HashSet<Tag>();

	public TagHandler() {
		
	}
	
	public static void addCategory(String category) {
		Category cat = new Category(category);
		categorySet.add(cat);
	}
	
	public static void removeCategory(String category) {
		Tag cat = new Category(category);
		categorySet.remove(cat);
	}
	
	public static List<String> returnCategories(){
		ArrayList<String> categoryNames = new ArrayList<String>();
		Iterator<Tag> iter = categorySet.iterator();
		while(iter.hasNext()) {
			categoryNames.add(iter.next().getName());
		}
		return categoryNames;
	}
}