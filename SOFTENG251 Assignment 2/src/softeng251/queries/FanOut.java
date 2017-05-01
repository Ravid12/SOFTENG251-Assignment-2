package softeng251.queries;

import java.util.ArrayList;
import java.util.Map;
import softeng251.dependencies.Attribute;
import softeng251.dependencies.Dependency;

public class FanOut extends Query {

	public FanOut(ArrayList<Dependency> dependencies) {
		super(dependencies);
	}

	@Override
	public Map<String, Integer> processData() {
		// for each dependency:
		for (Dependency d : dependencies){
			// constructs a "code" with the source, kind and target of the current module, and adds it to a list of codes
			addCode(d, Attribute.SOURCE, Attribute.KIND, Attribute.TARGET);
		}
		// counts frequencies of each unique label
		findFrequenciesFromCodes();
		return (frequencies.isEmpty() ? null : frequencies);
	}
}