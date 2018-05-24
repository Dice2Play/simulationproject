package Simulation.Model;

import java.util.List;

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
		
		for(int index = 0; index < amountOfReplications; index++)
		{
			// Create RandomGraph
			// Note: for the 'RandomGraph_Erdos' the amount of stubs at least needs to be as large as the amount of nodes * amount of possible connections
			//RandomGraph randomGraph = new RandomGraph_Configuration(5,10);
			RandomGraph randomGraph = new RandomGraph_Erdos(100,.51,10);
					
			// Calculate
			randomGraph.Calculate();
					
			// Get distribution graph
			randomGraph.getDistanceBetweenTwoRandomNodes();		

		}

	}
	
}
