package mentor.qa.selenium;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("1", "test");
		map.put("2", "test2");
		map.put("3", "test3");
		
		Set<String> set =  new TreeSet<String>();
		set = map.keySet();
		
		String[] list = set.toArray(new String[set.size()]);
		System.out.println(list[1]);
		

	}

}
