package Simulation.Model.Process;

import java.util.ArrayList;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Queue.QueueManager;
import Simulation.Model.Resource.ResourceManager;

public class Process {

	private final String ID;
	private final int processTime; // Amount of time-units needed for completing process. 
	private final Resource_Type type; 
	
	
	public Process(String ID, int processTime, Resource_Type type)
	{
		this.ID = ID;
		this.processTime = processTime;
		this.type = type;
	}
	
	boolean CanFire()
	{
		return ResourceManager.CheckForAvailableResource(type);
	}
	
	void Fire()
	{
		// Fire process
		int resourceCapacityFilled = QueueManager.SeizeQueueObject(processTime, ResourceManager.GetCapacityOfResource(type));
		ResourceManager.SeizeResource(type, resourceCapacityFilled, processTime, ID);

		
		
	}
	
	
	
}
