package simulation.process;

import java.util.ArrayList;
import java.util.List;

import simulation.entity.Entity;
import simulation.interfaces.Tick_Listener;
import simulation.process.behavior.CanFireBehavior;
import simulation.process.behavior.CanFireProcessResourceAndEntity;
import simulation.process.behavior.ProcessFire;
import simulation.queue.Queue;
import simulation.resource.Resource;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.time.Event_Type;
import simulation.time.TimeEvent;
import simulation.time.TimeManager;

public class Process extends SequenceObject{

	private List<Resource_Type> typeOfResourcesNeeded = new ArrayList<Resource_Type>();
	private ArrayList<Resource> seizedResources = new ArrayList<Resource>();
	private double processTime;
	private boolean isAvailable = true;
	

	
	public Process(String ID, double processTime)
	{
		this(ID, Process_Priority.Normal, processTime);
	}
	
	public Process(String ID, Process_Priority processPriority, double processTime)
	{
		super(ID, processPriority);
		this.processTime = processTime;
		fireBehavior = new ProcessFire();
		canFireBehavior = new CanFireProcessResourceAndEntity();
	}
	
	public void SetIsAvailable(boolean newValue)
	{
		isAvailable = newValue;
	}
		
	public void AddRequiredResource(Resource_Type typeOfResourceNeeded)
	{
		typeOfResourcesNeeded.add(typeOfResourceNeeded);
	}
	
	private boolean IsAvailable()
	{
		return isAvailable;
	}
	
	ArrayList<Resource> GetSeizedResources()
	{
		return seizedResources;
	}

	public boolean AreResourcesAvailable()
	{
		for(Resource_Type typeOfResource : typeOfResourcesNeeded)
		{
			if(!ResourceManager.GetInstance().CheckForAvailableResource(typeOfResource)) 
			{
				return false;
			}
		}
		
		// Default value
		return true;
	}

	
	double GetProcessTime()
	{
		return processTime;
	}
	
	

	
	

}
