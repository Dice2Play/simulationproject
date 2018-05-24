package Simulation.Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Simulation.Core.MeasureDistance;
import Simulation.Core.Node;
import Simulation.Core.RandomGraph;
import Simulation.Core.RandomGraph_Configuration;
import Simulation.Core.RandomGraph_Erdos;

public class SimulationModel {

	
	
	public SimulationModel()
	{
		DistanceDistribution(30);
	}
	
	private void DistanceDistribution(int amountOfReplications)
	{
		
		HashMap distributionValues =  new HashMap();
		
		for(int index = 0; index < amountOfReplications; index++)
		{
			// Create RandomGraph
			// Note: for the 'RandomGraph_Erdos' the amount of stubs at least needs to be as large as the amount of nodes * amount of possible connections
			//RandomGraph randomGraph = new RandomGraph_Configuration(5,10);
			RandomGraph randomGraph = new RandomGraph_Erdos(100,.51,10);
					
			// Calculate
			randomGraph.Calculate();
					
			// Get distanceBetweenTwoRandomNodes
			int distanceBetweenTwoRandomNodes = randomGraph.getDistanceBetweenTwoRandomNodes(); 
			
			// Add to hashmap
			if(distributionValues.containsKey(distanceBetweenTwoRandomNodes)) // Check if key already exists
			{
				distributionValues.put(distanceBetweenTwoRandomNodes, (int)distributionValues.get(distanceBetweenTwoRandomNodes) + 1);
			}
			
			else
			{
				distributionValues.put(distanceBetweenTwoRandomNodes, 1);
			}
			
		}
		
		// Print distance distribution 
		System.out.println("\n==== DISTANCE DISTRIBUTION ====");
		
		// Display values
	    Iterator it = distributionValues.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	    }
		
		

	}
	
	
	
}
