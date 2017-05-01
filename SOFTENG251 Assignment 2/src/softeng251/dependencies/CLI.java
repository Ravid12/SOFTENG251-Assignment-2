package softeng251.dependencies;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import softeng251.queries.Aggregates;
import softeng251.queries.DepCount;
import softeng251.queries.FanIn;
import softeng251.queries.FanOut;
import softeng251.queries.Query;
import softeng251.queries.Statics;
import softeng251.queries.Summary;
import softeng251.queries.Uses;


public class CLI {
	
	ArrayList <Dependency> dependencies;
	
	//Default Constructor
	public CLI() throws IOException{
		this("", "");
	}
	
	//Main constructor and Main series of events
	public CLI(String inputFileLocation, String queryRequest) throws IOException{
		displayInitialInformation(inputFileLocation, queryRequest);		
		dependencies = getDependenciesFromFile(inputFileLocation);
		Query query=chooseQuery(queryRequest);
		Map <String, Integer> outputMap = query.processData();
		displayData(outputMap);
	}
	
	
	public static void main (String args[]) throws IOException{
		//Checks if user input correctly
		if (args.length == 2){
			new CLI(args[0],args[1]);
		}else{
			throw new RuntimeException("Incorrect Arguments Given");
		}
	}
	
	
	public void displayInitialInformation(String inputFileLocation, String queryRequest) {
		System.out.println("QUERY\t" + queryRequest);
		System.out.println("DATAID\t" + inputFileLocation.substring(inputFileLocation.lastIndexOf('\\')+1, inputFileLocation.length()));
	}
	
	
	public Query chooseQuery(String queryRequest){
		if(queryRequest.equals("Summary")){ 
			return new Summary(dependencies);
		} else if (queryRequest.equals("DepCount")){
			return new DepCount(dependencies);
		} else if (queryRequest.equals("FanOut")){
			return new FanOut(dependencies);
		} else if (queryRequest.equals("FanIn")){
			return new FanIn(dependencies);
		} else if (queryRequest.equals("Uses")){
			return new Uses(dependencies);
		} else if (queryRequest.equals("Static")){
			return new Statics(dependencies);
		} else if (queryRequest.equals("Aggregates")){
			return new Aggregates(dependencies);
		} else{
		throw new RuntimeException("invalid Query");
		}	
	}

	
	public ArrayList<Dependency> getDependenciesFromFile(String fileLocation) throws IOException{
		ArrayList<Dependency> dependencies= new ArrayList<Dependency>();
		try {
			String currentLine;
			//initializes BufferedReader
			FileReader fr = new FileReader (fileLocation);
			BufferedReader br = new BufferedReader (fr);
			
			//While not at the end of the file:
			while (( currentLine = br.readLine()) != null){
				//Skips all empty lines and lines starting with '#'
				if(currentLine.length()>0 && currentLine.charAt(0) != '#'){
					//turns each valid row into a dependency and adds it to a list of dependencies
					dependencies.add(new Dependency(currentLine));
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return dependencies;
	}
	
	
	public void displayData(Map<String, Integer> displayMap) {
		// If any data was actually returned:
		if (displayMap != null){
			// Display each entry in the map with the appropriate output format
			for(Map.Entry<String,Integer> entry : displayMap.entrySet()) {
				String label = entry.getKey();
				Integer value = entry.getValue();
				System.out.println(label + "\t" + value);
			}
		}else {
			System.out.println("No results");
		}
	}
}
