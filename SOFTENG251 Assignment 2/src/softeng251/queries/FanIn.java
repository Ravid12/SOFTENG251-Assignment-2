package softeng251.queries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import softeng251.dependencies.Attribute;
import softeng251.dependencies.Dependency;

public class FanIn extends Query {
	
	public FanIn(ArrayList<Dependency> dependencies) {
		super(dependencies);
	}
	private Set<String> declaredTargets = new HashSet<String>();
	private Set<String> sources = new HashSet<String>();

	@Override
	public Map<String, Integer> processData() {
		// finds all declared targets, I.e. targets in source.
		for (Dependency d : dependencies){
			sources.add(d.getAttribute(Attribute.SOURCE));
			declaredTargets.add(d.getAttribute(Attribute.TARGET));	
		}
		declaredTargets.retainAll(sources);
		
		// for each dependency:
		for (Dependency d : dependencies){
			// checks if the dependency is a declared target
			if (declaredTargets.contains(d.getAttribute(Attribute.TARGET))){
				// constructs a "code" with the target, target's kind and source of the current module, and adds it to a list of codes
				addCode(d, Attribute.TARGET, Attribute.TARGET_KIND, Attribute.SOURCE);
			}
		}
		
		// counts frequencies of each unique label
		frequencies = findFrequenciesFromCodes();
		return (frequencies.isEmpty() ? null : frequencies);
	}	
}
