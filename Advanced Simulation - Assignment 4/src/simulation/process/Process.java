package simulation.process;

import java.util.ArrayList;
import java.util.List;

import simulation.entity.Entity;
import simulation.interfaces.Tick_Listener;
import simulation.process.behavior.CanFireBehavior;
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

	private boolean AreResourcesAvailable()
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

	/**
	 * Check for available:
	 * - Entity
	 * - Resource(typeOfResourcesNeeded)
	 * - Process
	 */
	public boolean CanFire() {
		return IsThereANextEntityFromQueue() && AreResourcesAvailable() && IsAvailable(); 
	}

	@Override
	public void Fire() throws Exception {
		super.Fire();
		Delay();
		
		// Create time-event on which process must release resources
		double timeOnWhichEventMostOccur = TimeManager.GetInstance().GetCurrentTime() + processTime;
		TimeManager.GetInstance().AddTimeEvent(new TimeEvent(timeOnWhichEventMostOccur,
				new ReleaseProcessCommand(this),
				String.format("Process [%s] released resources", this.GetID()),
				Event_Type.GENERAL));
	}
	
	public void Delay() throws Exception
	{
		// Seize resources
		for(Resource_Type resourceType : typeOfResourcesNeeded)
		{
			Resource resourceToSeize = ResourceManager.GetInstance().GetAvailableResource(resourceType);
			seizedResources.add(resourceToSeize);
			resourceToSeize.Seize();
		}
		
		// Seize entity
		currentEntity.Seize();
		
		// Seize this process
		isAvailable = false;
				
	}
	
	double GetProcessTime()
	{
		return processTime;
	}
	
	Entity GetCurrentEntity()
	{
		return currentEntity;
	}

	@Override
	public void SetNextSequenceObjectForEntity() {
		// Get next sequenceObject
		SequenceObject nextSequenceObject = linkedSequenceObjects.getFirst().GetNextSequenceObject(); 
		
		// Set next sequenceObject for entity
		currentEntity.SetCurrentSequenceObject(nextSequenceObject);
		
		// Adds entity to queue of next sequenceObject
		nextSequenceObject.AddEntityToQueue(currentEntity);
	}
	
	

}
