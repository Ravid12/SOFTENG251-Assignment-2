package softeng251.queries;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import softeng251.dependencies.Attribute;
import softeng251.dependencies.Dependency;
import softeng251.utilities.ValueComparator;

public class DepCount extends Query {
	
	public DepCount(ArrayList<Dependency> dependencies) {
		super(dependencies);
	}

	@Override
	public Map<String, Integer> processData() {
		
		/*
		 *  Counts frequency of each unique label.
		 *  A label is a String which has the module name, followed by a space with the module kind in round brackets. 
		 */
		for (Dependency d: dependencies){
			Integer freq;
			if (!d.getAttribute(Attribute.TARGET).equals("")){
				String Label=createLabel(d.getAttribute(Attribute.SOURCE), d.getAttribute(Attribute.KIND));
				freq = frequencies.get(Label); 
				frequencies.put(Label, freq+1 );
			}
		}

		// Sorts output data by key before returning it.
		TreeMap<String, Integer> sortedModuleFrequency = sortMapByValue(frequencies);
		if(sortedModuleFrequency.isEmpty()){
			return null;
		} else {	
			  return (sortedModuleFrequency);
		}

	}
	
	private TreeMap<String, Integer> sortMapByValue(Map<String, Integer> map){
		Comparator<String> comparator = new ValueComparator(map);
		/*
		 * TreeMap is a map sorted by its keys.
		 * The comparator sorts the TreeMap by values. 
		 * If 2 values are the same, the comparator sorts alphabetically by key.
		 */
		TreeMap<String, Integer> result = new TreeMap<String, Integer>(comparator);
		result.putAll(map);
		
		return result;
	}
}