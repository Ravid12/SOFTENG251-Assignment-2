package softeng251.dependencies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Dependency {

	private Map <Attribute, String> attributeList = new HashMap<Attribute, String>();
		
	//Default Constructor
	public Dependency(){
		this("");
	}
	
	//Main Constructor
	public Dependency (String attributes){
		// Takes in row of attributes and assigns each one the appropriate key.
		ArrayList<String> currentAttributes = new ArrayList<String>();				
		currentAttributes.addAll(Arrays.asList(attributes.split("\t")));
		
		int i=0;
		for (Attribute a : Attribute.values()){
			// If the row is a module with no dependency, then fill the rest of the attributes with empty strings.
			if (i<currentAttributes.size()){
				attributeList.put(a, currentAttributes.get(i));
				i++;
			} else {
				attributeList.put(a, "");
			}
		}
	}
	
	//Get an attribute from attributeList with a specified key.
	public String getAttribute(Attribute source){
		return attributeList.get(source);
	}
}
