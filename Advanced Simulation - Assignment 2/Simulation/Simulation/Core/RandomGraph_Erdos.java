package Simulation.Core;

public class RandomGraph_Erdos extends RandomGraph{

	public RandomGraph_Erdos(int degree, double probability, int amountOfNodes) {
		super(degree, probability, amountOfNodes);	
	}
	
	

	public void Calculate() {
		
		for(Node nodeA : nodes)
		{
			// Check for a free stub
			while(nodeA.HasNonConnectedStub())
			{
				Stub nodeANonConnectedStub = nodeA.getNonConnectedStub();
				
				
				// Loop through all other nodes
				for(Node nodeB : nodes)
				{
							
					// Check if nodeB has any free stubs not equal to <nodeANonConnectedStub>
					if(nodeB.HasNonConnectedStub(nodeANonConnectedStub))
					{
						Stub nodeBNonConnectedStub = nodeB.getNonConnectedStub(nodeANonConnectedStub);
						
						// Connect stubs with each other
						nodeANonConnectedStub.setNode(nodeB);
						nodeBNonConnectedStub.setNode(nodeA);
						
						System.out.println(String.format("Connected node %s, with stub index %s, to node %s with stub index %s", 
								nodeA.getID(), nodeA.getStubIndex(nodeANonConnectedStub), nodeB.getID(), nodeB.getStubIndex(nodeBNonConnectedStub)));
						
						// Continue to next node
						break;
						
					}
				}
			}

		}
	}

}
