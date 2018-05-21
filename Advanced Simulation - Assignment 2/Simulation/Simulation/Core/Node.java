package Simulation.Core;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Node {

	private Point coordinates;
	private List<Stub> stubs = new ArrayList<Stub>();
	private final int amountOfStubs;
	private final String ID;
	
	
	
	public Node(int amountOfStubs, String ID)
	{
		this.amountOfStubs = amountOfStubs;
		this.ID = ID;
		CreateStubs();
	}
	
	public String getID()
	{
		return ID;
	}
	
	private void CreateStubs()
	{
		for(int index = 0; index < amountOfStubs; index++)
		{
			stubs.add(new Stub());
		}
	}
	
	public Point getCoordinates()
	{
		return coordinates;
	}
	
	public void setCoordinates(Point newCoordinates)
	{
		coordinates = newCoordinates;
	}
	
	// Checks for Non Connected stub, with the constraint that the returned stub is not equal to the input stub.
	public boolean HasNonConnectedStub(Stub stubNodeA)
	{
		for(Stub stub : stubs)
		{
			if((stub.getNode() == null) && (stubNodeA != stub))
			{
				return true;
			}
		}
		
		// If no free unique stub could be found, return false
		return false;
	} 
	
	// Checks for Non Connected stub
	public boolean HasNonConnectedStub()
	{
		for(Stub stub : stubs)
		{
			if((stub.getNode() == null))
			{
				return true;
			}
		}
		
		// If no free stub could be found, return false
		return false;
	}
		
	public void ConnectNodeToStub(Node nodeToConnect)
	{
		// Get non-connected stub
		Stub freeStub = getNonConnectedStub();
		
		// Set stub - node
		freeStub.setNode(nodeToConnect);
	}
	
	// Returns a non connected stub from the node
	public Stub getNonConnectedStub()
	{
		for(Stub stub : stubs)
		{
			if(stub.getNode() == null)
			{
				return stub;
			}
		}
		
		// If no free node could be found, return null
		return null;
	}
	
	// Returns a non connected stub from the node, with the constraint that the returned stub is not equal to the input stub.
	public Stub getNonConnectedStub(Stub stubNodeA)
		{
			for(Stub stub : stubs)
			{
				if((stub.getNode() == null) && (stubNodeA != stub))
				{
					return stub;
				}
			}
			
			// If no free unique stub could be found, return null
			return null;
		}

	// Get stub index
	public int getStubIndex(Stub stub)
	{
		return stubs.indexOf(stub);
	}
	
	
}
