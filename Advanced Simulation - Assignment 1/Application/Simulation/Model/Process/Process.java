package Simulation.Model.Process;

import java.util.ArrayList;

import Simulation.Enums.Resource_Types;
import Simulation.Model.Queue.QueueManager;
import Simulation.Model.Queue.QueueObject;
import Simulation.Model.Resource.Resource;
import Simulation.Model.Resource.ResourceManager;

public class Process {

	private String ID;
	private int processTime; // Amount of time-units needed for completing process. 
	private ResourceManager resourceManager = new ResourceManager();
	private QueueManager queueManager = new QueueManager();
	private Resource_Types type; 
	
	
	public Process(String ID, int processTime, Resource_Types type)
	{
		this.ID = ID;
		this.processTime = processTime;
		this.type = type;
	}
	
	
	
	public boolean CanFire()
	{
		// Retrieve for each queue first queue_object in line
		int[] groupSizes = queueManager.GetNextGroupSizes();
		
		for(int groupSize : groupSizes)
		{
			// Check if there are available resources with given groupsize
			if(resourceManager.CheckForAvailableResource(type, groupSize))
			{
				return true;
			}
			
		}
		
		// If no 
		return false;
	}
	
	public void Fire()
	{
		// Get next group and seize resource
		// Retrieve for each queue first queue_object in line
		int[] groupSizes = queueManager.GetNextGroupSizes();
		
		for(int groupSize : groupSizes)
		{
			// Check if there are available resources with given groupsize
			if(resourceManager.CheckForAvailableResource(type, groupSize))
			{
				// Fire process
				resourceManager.SeizeResource(Resource_Types.BOAT, groupSize, processTime, ID);
				break;
			}
			
		}
		
	}
	
	
	
}
