package mapping;
import java.util.*;

public class Tester {

	public static void main(String[] args){
		TagHandler.addCategory("ONE");
		TagHandler.addCategory("TWO");
		TagHandler.addCategory("THREE");
		TagHandler.addCategory("FOUR");
		TagHandler.addCategory("FIVE");
		
		List<String> list = TagHandler.returnCategories();
		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		
		
	}
}