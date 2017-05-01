package softeng251.queries;

import java.util.ArrayList;
import java.util.Map;
import softeng251.dependencies.Attribute;
import softeng251.dependencies.Dependency;

public class Statics extends Query {
	
	public Statics(ArrayList<Dependency> dependencies) {
		super(dependencies);
	}

	@Override
	public Map<String, Integer> processData() {
		for (Dependency d : dependencies){
			// checks if the dependency's category is used as a Static type in the module
			if (d.getAttribute(Attribute.CATEGORY).contains("Static")){
				// constructs a "code" with the source, kind and target of the current module, and adds it to a list of codes
				addCode(d, Attribute.SOURCE, Attribute.KIND, Attribute.TARGET);
			}
		}
		// counts frequencies of each unique label
		frequencies = findFrequenciesFromCodes();
		return (frequencies.isEmpty() ? null : frequencies);
	}
}
