package simulation.process;

import java.util.ArrayList;
import java.util.List;

import simulation.queue.Queue;
import simulation.resource.Resource_Type;

public class Process {

	private Queue queue;
	private String ID;
	private List<Resource_Type> typeOfResourcesNeeded = new ArrayList<Resource_Type>();
	
	
	public Process(String ID)
	{
		this.ID = ID;
	}
	
	public void SetQueue(Queue queue)
	{
		this.queue = queue;
	}
	
	public void AddRequiredResource(Resource_Type typeOfResourceNeeded)
	{
		typeOfResourcesNeeded.add(typeOfResourceNeeded);
	}
	
	
	
}
