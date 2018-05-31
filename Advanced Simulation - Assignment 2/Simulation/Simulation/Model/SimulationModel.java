package Simulation.Model;

import java.util.ArrayList;
import java.util.Collections;
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
		DistanceDistribution(21);
        
	}
	
	private void DistanceDistribution(int amountOfReplications)
	{
		//timeline with each distance, no counting 
		List<ArrayList<Integer>> timeline = new ArrayList<ArrayList<Integer>>();
		//record all the distance number has shown up, from 1, 2, 3, ..if it is have a even bigger one like 10.
		List<Integer> distancelist = new ArrayList<Integer>();
	
		//distibution map Y[timeline] + X[count each distance distribution arrigation]
		List<ArrayList<Integer>> distributionMap = new ArrayList<ArrayList<Integer>>(amountOfReplications);
		
		//HashMap distributionValues =  new HashMap();
		
		for(int index = 0; index < amountOfReplications; index++)
		{
			// Create RandomGraph
			// Note: for the 'RandomGraph_Erdos' the amount of stubs at least needs to be as large as the amount of nodes * amount of possible connections
			RandomGraph randomGraph = new RandomGraph_Configuration(4,10);
			
			//RandomGraph randomGraph = new RandomGraph_Erdos(100,.21,10);
			
			System.out.println("---------Random graph created ---------Round/At Time " + index);		
			// Calculate
			randomGraph.Calculate();
			
			System.out.println("---------distance distribution Record START ---------Round/At Time " + index);
			
			ArrayList<Integer> distance = randomGraph.getDistanceBetweenEachNode();
			
			//add all the possible distance/steps to distance list 
			for(int i : distance)
			{
				if(!distancelist.contains(i))
				{
					distancelist.add(i);
				}	
			}
			
			Collections.sort(distancelist); // sort the list, beucz sometime might the bigger step shows first.
			
			timeline.add(distance); // in the end the timeline add the distance of each graph.
			
			System.out.println("---------distance distribution Record END--------- Round/Time " + index);
		
			/*// Get distanceBetweenTwoRandomNodes
			int distanceBetweenTwoRandomNodes = randomGraph.getDistanceBetweenTwoRandomNodes(); 
			if (distanceBetweenTwoRandomNodes < 0) continue;  no path between the two random nodes 

			// Add to hashmap
			if(distributionValues.containsKey(distanceBetweenTwoRandomNodes)) // Check if key already exists
			{
				distributionValues.put(distanceBetweenTwoRandomNodes, (int)distributionValues.get(distanceBetweenTwoRandomNodes) + 1);
			}
			
			else
			{
				distributionValues.put(distanceBetweenTwoRandomNodes, 1);
			}
			*/
			System.out.println("----------Print the distribution matrix-----------------");
			
			ArrayList<Integer> counters = new ArrayList<Integer>();
			for(int i : distancelist)
			{
			  int aggregate = calculateDistanceditribution(index,timeline, i);
			  counters.add(aggregate);			  
			}
			distributionMap.add(counters);			
		}//loop of all times end
		
		// Print distance distribution 
		System.out.println("\n==== DISTANCE DISTRIBUTION ====");
		printTimeMatrtix(timeline);
		
		// Display values
/*	    Iterator it = distributionValues.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	    }
		*/
		System.out.println("\n==== DISTANCE DISTRIBUTION with Aggregate value per 1, 2, 3, 4, 5,..distance====");
		
		printDistanceDistributionPerTime(distributionMap);

	}


	/**
	 * print out time matrix
	 *
	 * @timeline the time list which record each repeated new generation random graph
	 */
	private void printTimeMatrtix(List<ArrayList<Integer>> timeline )
	{	
		for(int i = 0; i < timeline.size(); i++){
			String result = " ";
	          for(int j = 0; j < timeline.get(i).size(); j++)
	          {
	               result += timeline.get(i).get(j);
	               result = result + ",";
	          }
	          // replaced the call to System.out.println() with "\n"..	          
	          System.out.println("Time "+ i +" : "+ result);
	      }	      
	 }
	/**
	 * @timeUnit : the i_th time for iteration
	 * @timeline : the timeline store each distance
	 * @distance:  the step of which it is searching for shown each graph. 
	 */
	private int calculateDistanceditribution(int timeUnit,List<ArrayList<Integer>> timeline,int distance)
	{
		int counter = (int) timeline.get(timeUnit)
				.stream()
				.filter(e -> e== distance)
				.count();		
		System.out.println(" Distance/steps "+ distance+ " appear: "+ counter+ " times");
		return counter;
	}
	/**print out each time the graph's distance distribution.
	 * @map_distance: the time map X[diatance distribution] and Y[timeline]
	 */
	private void printDistanceDistributionPerTime(List<ArrayList<Integer>> map_distance)
	{
		
		for(int i = 0; i < map_distance.size(); i++)
		{
			System.out.println("at time "+i+": "+ map_distance.get(i));
		}
        
	}
	
		
	
}
