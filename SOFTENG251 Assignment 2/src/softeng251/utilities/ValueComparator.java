package softeng251.utilities;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

//a comparator that compares Strings
public class ValueComparator implements Comparator<String>{

	Map<String, Integer> map = new HashMap<String, Integer>();

	public ValueComparator(Map<String, Integer> map){
		this.map.putAll(map);
	}

	/*
	 * Comparator for sorting a TreeMap<String, Integer> by values. 
	 * If 2 values are the same, sort Keys Alphabetically
	 */
	@Override
	public int compare(String s1, String s2) {
		if(map.get(s1) > map.get(s2)){
			return -1;
		}else if (map.get(s1) < map.get(s2)){
			return 1;
		}	
		else {
			return (s1.compareTo(s2));
		}
			
	}
}