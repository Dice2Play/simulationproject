package Simulation.Core;

import java.util.ArrayList;
import java.util.List;

public abstract class RandomGraph {

	protected List<Node> nodes = new ArrayList<Node>();
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
	
	
	
}
