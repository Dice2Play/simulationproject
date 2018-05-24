package Simulation.Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class RandomGraph {

	protected ArrayList<Node> nodes = new ArrayList<Node>();
	protected ArrayList<Integer> distanceDistribution = new ArrayList<Integer>();
	private final int degree;
	protected  double probability;
	private final int amountOfNodes;
	
	
	public RandomGraph(int degree, double probability, int amountOfNodes )
	{
		this.degree = degree;
		this.probability = probability;
		this.amountOfNodes = amountOfNodes;
		
		// Create nodes
		CreateNodes();
		
	}
	
	public RandomGraph(int degree, int amountOfNodes )
	{
		this.degree = degree;
		this.amountOfNodes = amountOfNodes;
		
		// Create nodes
		CreateNodes();
		
	}
	
	// Calculates random graph (different for each kind of random graph)
	public abstract void Calculate();
	
	private void CreateNodes()
	{
		for(int index = 0; index < amountOfNodes; index++)
		{
			String nodeID = Integer.toString(index);
			
			nodes.add(new Node(degree, nodeID));
		}
	}
	
	public ArrayList<Node> getNodesWithNonConnectedStubs()
	{
		List<Node> nodesWithOpenStubs = new ArrayList<Node>();
		
		for(Node node : nodes)
		{
			if(node.HasNonConnectedStub())
			{
				nodesWithOpenStubs.add(node);
			}
		}
		
		
		return (ArrayList<Node>) nodesWithOpenStubs;
	}
	
	public ArrayList<Node> getAllNodes()
	{
		return nodes;
	}
	
	public int getDistanceBetweenTwoRandomNodes()
	{
		// Get two random nodes
		Random rand = new Random();

		Node sourceNode = getAllNodes().get(rand.nextInt(getAllNodes().size()));
		Node targetNode = getAllNodes().get(rand.nextInt(getAllNodes().size()));
		while(sourceNode == targetNode) { targetNode = getAllNodes().get(rand.nextInt(getAllNodes().size()));} // Keep running till we have two unique nodes
			
		try 
		{
			return MeasureDistance.getDistanceBetweenNodes(sourceNode, targetNode, getAllNodes());
		}
			
		catch(Exception ex)
		{
			ex.printStackTrace();
			// if no path could be found
			return Integer.MAX_VALUE;
		}
	}
	/**
	**Checking all the notes distance one to the rest and record all the distance
	* Adding all the distance to a distance array
	**/
	public ArrayList<Integer> getDistanceBetweenEachNode()
	{
		ArrayList<Node> myNode = this.getAllNodes();
        for(int i = 0; i < myNode.size(); i++)
        {
        	Node sourceNode = myNode.get(i);
        		for(int j = i+1; j < myNode.size(); j++)
        		{
        			Node targetNode = myNode.get(j);
					int distance;
					try {
						distance = MeasureDistance.getDistanceBetweenNodes(sourceNode, targetNode, getAllNodes());
						if(distance >= 0) {
							/* negative distance means no path between two nodes; so we only add positive distances */
							distanceDistribution.add(distance);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}	
        		}

        }
        return distanceDistribution;
	}

				
	
	
}
