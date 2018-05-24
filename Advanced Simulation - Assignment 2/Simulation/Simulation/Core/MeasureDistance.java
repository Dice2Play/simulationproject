package Simulation.Core;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

// Dijkstra's algorithm and such

public class MeasureDistance {

	
	public static int getDistanceBetweenNodes(Node sourceNode, Node targetNode, ArrayList<Node> allNodes) throws Exception
	{

		// Create distance graph
		ArrayList<DijkstraNodeObject> visitedNodes = CreateSmallestDistanceGraph(sourceNode,allNodes);
		
		// Get dijkstra node
		DijkstraNodeObject dnoHoldingTargetNode = getDNOFromList(visitedNodes, targetNode, sourceNode);
		
		
		System.out.println(String.format("Smallest distance between %s and %s is %d", sourceNode.getID(), targetNode.getID(), DistanceToSourceObject(dnoHoldingTargetNode)));
		
		// Return shortest distance to source node
		return dnoHoldingTargetNode.getDistanceToSourceNode();
	}
	
	private static ArrayList<DijkstraNodeObject> CreateSmallestDistanceGraph(Node source,  ArrayList<Node> allNodes)
	{
		// Set (un)visited node lists
		LinkedList<DijkstraNodeObject> unvisitedNodes = GenerateDijkstraNodeObjects(allNodes.toArray(new Node[allNodes.size()]));
		ArrayList<DijkstraNodeObject> visitedNodes = new ArrayList<DijkstraNodeObject>(); 
		
		// Create source + nodes to visit
		List<DijkstraNodeObject> nodesToVisit = new CopyOnWriteArrayList<DijkstraNodeObject>();
		
		DijkstraNodeObject sourceNode = new DijkstraNodeObject(source);
		sourceNode.setDistanceToPreviousNode(0);
		nodesToVisit.add(sourceNode);

		
		
		while(nodesToVisit.size() != 0)
		{
			// Get first element of nodesToVisit
			DijkstraNodeObject currentVisitingNode = nodesToVisit.get(0); 
			
			// Get unvisited neighbors
			List<DijkstraNodeObject>  unvisitedNeighbors = getUnvisitedNeighbors(currentVisitingNode, unvisitedNodes);
										
			// Set previous node + shortest distance for each unvisited neighbor, with the constraint that the current distance is shorter than the one already set.
			for(DijkstraNodeObject dno : unvisitedNeighbors)
			{
				// Calculate distance from unvisitedNeighbor to source
				//int distanceToSourceObject = DistanceToSourceObject(dno);
				int distanceToSourceObject = 1;
				
				
				if(dno.isNewDistanceShorter(distanceToSourceObject))
				{
					dno.setDistanceToPreviousNode(distanceToSourceObject);
					dno.setPreviousNode(currentVisitingNode);
				}
				
				
				// Add to 'nodesToVisit' if node hasnt been visited yet
				if(!visitedNodes.contains(dno))
				{
					nodesToVisit.add(dno);
				}
				
				
			}
						
			// Add to visited nodes, remove from unvisited nodes
			visitedNodes.add(currentVisitingNode);
			unvisitedNodes.remove(currentVisitingNode);
			nodesToVisit.remove(currentVisitingNode);
			
						
						
		}
		
		

		
		return visitedNodes;
		
		
		
	}

	private static List<DijkstraNodeObject> getUnvisitedNeighbors(DijkstraNodeObject currentNode, List<DijkstraNodeObject> currentUnvisitedNodes)
	{
		List<DijkstraNodeObject> unvisitedNeighbors = new ArrayList<DijkstraNodeObject>();
 		
		for(DijkstraNodeObject dno : currentUnvisitedNodes)
		{
			if(currentNode.hasConnectedNode(dno.node))
			{
				unvisitedNeighbors.add(dno);
			}
		}
		
		return unvisitedNeighbors;
		
	}
	
	private static LinkedList<DijkstraNodeObject> GenerateDijkstraNodeObjects(Node[] allNodes)
	{
		LinkedList<DijkstraNodeObject> dijkstraObjects = new LinkedList<DijkstraNodeObject>();
	
		for(Node node : allNodes)
		{
			dijkstraObjects.add(new DijkstraNodeObject(node));
		}
	
		return dijkstraObjects;
	}
	
	private static DijkstraNodeObject getDNOFromList(List<DijkstraNodeObject> listToSearchIn, Node nodeToFind, Node sourceNode) throws Exception
	{
		DijkstraNodeObject dnoHoldingNodeToFind = null;
		
		for(DijkstraNodeObject dno : listToSearchIn)
		{
			if(dno.HasSameNode(nodeToFind)) {return dno;}
		}
		
		// If the probability of two nodes connecting is very low, it could be that the source/target node doesnt exist
		if(dnoHoldingNodeToFind == null)
		{
			throw new Exception(String.format("There could no path be established between the source node: %s and node: %s.", sourceNode.getID(),nodeToFind.getID()));
		}
		
		
		
		return dnoHoldingNodeToFind;
	}
	
	private static int DistanceToSourceObject(DijkstraNodeObject target)
	{
		int distanceToSource = 0;
		
		if(target.hasPreviousNode())
		{
			distanceToSource = 1 + DistanceToSourceObject(target.getPreviousNode()); 
		}
		
		return distanceToSource;
	}
	
	static class DijkstraNodeObject
	{
		private Node node;
		private int distanceToPreviousNode = Integer.MAX_VALUE;
		private DijkstraNodeObject previousNode;
		
		
		public DijkstraNodeObject(Node node)
		{
			this.node = node;
		}
		
		public void setPreviousNode(DijkstraNodeObject newNode)
		{
			previousNode = newNode;
		}
		
		public DijkstraNodeObject getPreviousNode()
		{
			return previousNode;
		}
		
		public int getDistanceToSourceNode()
		{
			return distanceToPreviousNode;
		}
		
		public void setDistanceToPreviousNode(int newDistance)
		{
			distanceToPreviousNode = newDistance;
		}
		
		public boolean isNewDistanceShorter(int newDistance)
		{
			if(newDistance < distanceToPreviousNode)
			{
				return true;
			}
			
			else return false;
		}
		
		public boolean hasPreviousNode()
		{
			return (previousNode != null);
		}
		
		// Check if connected nodes equal input node
		public boolean hasConnectedNode(Node inputNode)
		{
			List<Node> connectedNodes = new ArrayList<Node>();
			Collections.addAll(connectedNodes, this.node.getConnectedNodes());
			return connectedNodes.contains(inputNode); 
		}
		
		public boolean HasSameNode(Node node)
		{
			return node.equals(this.node);
		}
		
		
	}
	
}
