package Simulation.Core;

import java.util.ArrayList;
import java.util.Random;

import Statistics.DiscreteUniformDistribution;
import Statistics.UniformDistribution;

public class RandomGraph_Configuration extends RandomGraph{

	
	
	
	public RandomGraph_Configuration(int degree, int amountOfNodes) {
		super(degree, amountOfNodes);
	}

	public void Calculate() {
		
		for(Node nodeA : nodes)
		{
			// First check if node has a non connected stub, if not continue to next node
			while(nodeA.HasNonConnectedStub()) 
			{
				// Get non connected stub from node A
				Stub nodeANonConnectedStub = nodeA.getNonConnectedStub();
				
				// Get all other nodes, with the constraint that the node needs to have a non-connected-stub
				ArrayList<Node> nodesWithNonConnectedStubs = getNodesWithNonConnectedStubs();
				
				// Pick any of the nodes above, using the uniform distribution
				double indexToPick = Probability.Probability.GetDistributionResult(new DiscreteUniformDistribution(0,nodesWithNonConnectedStubs.size() - 1, new Random()));
				Node nodeB = nodesWithNonConnectedStubs.get((int) indexToPick);
				Stub nodeBNonConnectedStub = nodeB.getNonConnectedStub(nodeANonConnectedStub); // Make sure that we don't connect to the same node-stub
				
				// If <nodeBNonConnectedStub> is null, then it means we got the same stub from the same node.
				// In this case continue the while loop
				if(nodeBNonConnectedStub == null) {continue;}
				
				
				// Connect
				nodeANonConnectedStub.setNode(nodeB);
				nodeBNonConnectedStub.setNode(nodeA);
				
				/*System.out.println(String.format("Connected node %s, with stub index %s, to node %s with stub index %s", 
						nodeA.getID(), nodeA.getStubIndex(nodeANonConnectedStub), nodeB.getID(), nodeB.getStubIndex(nodeBNonConnectedStub)));

				*/
				
				
				
				
				
			}
			
		}
	}
	
	
	

}
