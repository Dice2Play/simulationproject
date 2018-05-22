package Simulation.Model;

import java.util.List;

import Simulation.Core.Node;
import Simulation.Core.RandomGraph;
import Simulation.Core.RandomGraph_Configuration;
import Simulation.Core.RandomGraph_Erdos;

public class SimulationModel {

	
	private List<Node> nodes;
	
	public SimulationModel()
	{
		// Create RandomGraph
		//RandomGraph randomGraph = new RandomGraph_Configuration(5,10);
		RandomGraph randomGraph = new RandomGraph_Erdos(1,0.5,10);
		
		// Calculate
		randomGraph.Calculate();
		
		
	}
	
}
