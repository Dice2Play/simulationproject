package Simulation.Model.Process;

import Simulation.Model.Queue.QueueObject;
import Simulation.Model.Resource.Resource;

public class Process {

	private String ID;
	private QueueObject[] queues;
	private Resource[] resources;
	private int processTime = 4; // Amount of time needed for completing process. 
	
	
	public Process(String ID, Resource[] resources, QueueObject[] queues, int processTime)
	{
		this.ID = ID;
		this.resources = resources;
		this.queues = queues;
		this.processTime = processTime;
	}
	
	public boolean CanFire()
	{
		return false;
	}
	
	public void Fire()
	{
		
	}
	
	
	
}
