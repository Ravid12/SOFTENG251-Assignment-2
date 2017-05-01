package softeng251.queries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import softeng251.dependencies.Attribute;
import softeng251.dependencies.Dependency;

public abstract class Query {
	protected Set<String> codes = new HashSet<String>();
	protected Map<String, Integer> frequencies = new TreeMap<String, Integer>();
	protected ArrayList <Dependency> dependencies;
	
	
	public Query (ArrayList<Dependency> dependencies){
		this.dependencies=dependencies;
		for (Dependency d : dependencies){
			frequencies.put(createLabel(d.getAttribute(Attribute.SOURCE), d.getAttribute(Attribute.KIND)), 0);
		}
	}
	
	public abstract Map<String, Integer> processData();	
	
	/*
	 * Takes in a dependency and 3 attributes to build a "code" out of. 
	 * The first two attributes are used to create the module label,
	 * The third attribute is a relator, which is combined with the label to create the code.
	 * 
	 * The code allows us to count the number of unique "relator"s for a given label, by adding it to a set. This 
	 * removes all duplicate relators for a label.
	 * 
	 * A label is a String which has the module name, followed by a space with the module kind in round brackets. 
	 * A code is a String which has the label, followed by a "%" symbol and the "relator".
	 * 
	 * A code is not built if a module's target is the same as it's source name, or if it has no target.
	 */
	public void addCode (Dependency d, Attribute moduleName, Attribute moduleKind, Attribute relator){
		if (!(d.getAttribute(Attribute.TARGET).equals(d.getAttribute(Attribute.SOURCE))) && !(d.getAttribute(Attribute.TARGET).equals(""))){
			String label = createLabel(d.getAttribute(moduleName), d.getAttribute(moduleKind));
			codes.add(createCode(label, d.getAttribute(relator)));
			}
		}
		
	/*
	 * Takes a Set of codes and returns a Map, where the Key is a given label, and the value is the frequency 
	 * of that label, regardless of the "relator".
	 */
	public Map<String, Integer> findFrequenciesFromCodes(){
		for (String code : codes){
			String moduleLabel;
			
			//removes the "relator" from the code
			moduleLabel=Decode(code);	
			
			//counts frequency of a label
			Integer freq = frequencies.get(moduleLabel);
			frequencies.put(moduleLabel, freq+1);
		}
		
		return frequencies;
	}
	
	public String createLabel(String moduleName, String moduleKind){
		return moduleName + " (" + moduleKind + ")";
	}
	public String createCode(String label, String relator){
		return label+"%"+relator;
	}
	
	public String Decode(String code){
		return code.substring(0, code.lastIndexOf('%'));
	}
}
