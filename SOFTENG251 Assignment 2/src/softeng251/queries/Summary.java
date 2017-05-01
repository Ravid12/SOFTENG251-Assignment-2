package softeng251.queries;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import softeng251.dependencies.Attribute;
import softeng251.dependencies.Dependency;

public class Summary extends Query{
	
	public Summary(ArrayList<Dependency> dependencies) {
		super(dependencies);
	}

	private Map <String, Integer> summaryOutput = new LinkedHashMap<String, Integer>();

	@Override
	public Map<String, Integer> processData() {
		Set <String> uniqueDeclarations = new HashSet<String>();
		Set <String> uniqueTargets = new HashSet<String>();
		Set <String> declaredTargets;
		int totalSources, totalTargets, totalUndeclared, totalSourcesNoDeps=0, totalDependencies = 0;
		/*
		 * find all unique targets and sources.
		 * also counts all sources with and without dependencies separately.
		 */
		for (Dependency d: dependencies){
			if (!d.getAttribute(Attribute.TARGET).equals("")){
				totalDependencies ++;
				uniqueDeclarations.add(d.getAttribute(Attribute.SOURCE));	
				uniqueTargets.add(d.getAttribute(Attribute.TARGET));
			} else {
				totalSourcesNoDeps ++;
			}

		}
		
		/*
		 * finds number of unique sources and targets
		 */
		totalSources=uniqueDeclarations.size();
		totalTargets=uniqueTargets.size();
		
		/*
		 * finds all targets which have been declared, 
		 * hence, all targets which have also been undeclared
		 */
		declaredTargets=uniqueTargets;
		declaredTargets.retainAll(uniqueDeclarations);
		totalUndeclared = totalTargets-declaredTargets.size();
		
		summaryOutput.put("DEPS", totalDependencies);
		summaryOutput.put("SRCWITHDEPS", totalSources);
		summaryOutput.put("SRCNODEPS", totalSourcesNoDeps);
		summaryOutput.put("TGTNOTSRC", totalUndeclared);
		
		return summaryOutput;
	}
}
