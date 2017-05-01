package softeng251.queries;

import java.util.ArrayList;
import java.util.Map;
import softeng251.dependencies.Attribute;
import softeng251.dependencies.Dependency;

public class Uses extends Query {

	public Uses(ArrayList<Dependency> dependencies) {
		super(dependencies);
	}

	@Override
	public Map<String, Integer> processData() {
		//For each dependency
		for (Dependency d : dependencies){
			String currentCategory = d.getAttribute(Attribute.CATEGORY);
			//If the category contains "Invoke", "Get" or "Put"
			if (currentCategory.contains("Invoke") || currentCategory.contains("Get") || currentCategory.contains("Put") && (d.getAttribute(Attribute.TARGET_PACKAGE).length()>0)){
				//constructs a "code" with the source, kind and target of the current module, and adds it to a list of codes
				addCode(d, Attribute.SOURCE, Attribute.KIND, Attribute.TARGET);
			}
		}
		// counts frequencies of each unique label
		frequencies = findFrequenciesFromCodes();
		return (frequencies.isEmpty() ? null : frequencies);
	}
	
}
