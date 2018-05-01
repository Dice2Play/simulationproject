package Simulation.Model.Process;

import java.util.ArrayList;

import Simulation.Enums.Resource_Type;
import Simulation.Model.Queue.QueueManager;
import Simulation.Model.Queue.QueueObject;
import Simulation.Model.Resource.Resource;
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
	
	public boolean CanFire()
	{
		return ResourceManager.CheckForAvailableResource(type);
	}
	
	public void Fire()
	{
		// Fire process
		//resourceManager.SeizeResource(type, queueObject.GetGroupSize(), processTime, ID);
		//queueManager.SeizeQueueObject(queueObject.GetQueueID(), processTime, int );
	}
	
	
	
}
