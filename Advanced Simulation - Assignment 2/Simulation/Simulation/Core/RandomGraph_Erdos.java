package Simulation.Core;

import java.util.Random;

import Probability.Probability;
import Statistics.BernoulliDistribution;

public class RandomGraph_Erdos extends RandomGraph{

	public RandomGraph_Erdos(int degree, double probability, int amountOfNodes) {
		super(degree, probability, amountOfNodes);	
	}
	

	public void Calculate() {
		
		for(Node nodeA : nodes)
		{
			
			// Loop through all other nodes
			for(Node nodeB : nodes)
			{
				// If nodes are equal, continue to next node.
				if(nodeA == nodeB) {continue;}
				
				
				// Use bernoulli distribution to determine whether stubs should be connected
				// If 0, continue to next node, if 1, connect
				if(Probability.GetDistributionResult(new BernoulliDistribution(probability, new Random())) == 1)
				{
					
					// Connect nodes with each other
					nodeA.ConnectNodeToStub(nodeB);
					nodeB.ConnectNodeToStub(nodeA);
					
					System.out.println(String.format("Connected node %s to node %s", 
							nodeA.getID(), nodeB.getID()));
				}
			
			}
			
		}
	}

}
