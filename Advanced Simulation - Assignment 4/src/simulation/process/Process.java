package simulation.process;

import java.util.ArrayList;
import java.util.List;

import simulation.entity.Entity;
import simulation.interfaces.Tick_Listener;
import simulation.queue.Queue;
import simulation.resource.ResourceManager;
import simulation.resource.Resource_Type;
import simulation.time.Event_Type;

public class Process extends SequenceObject implements Tick_Listener{

	private List<Resource_Type> typeOfResourcesNeeded = new ArrayList<Resource_Type>();
	private double processTime;
	private double finishTime;

	
	public Process(String ID, double processTime)
	{
		this(ID, Process_Priority.Normal, processTime);
	}
	
	public Process(String ID, Process_Priority processPriority, double processTime)
	{
		super(ID, processPriority);
		this.processTime = processTime;
		
		
	}
		
	public void AddRequiredResource(Resource_Type typeOfResourceNeeded)
	{
		typeOfResourcesNeeded.add(typeOfResourceNeeded);
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
	 */
	public boolean CanFire() {
		return IsThereANextEntityFromQueue() && AreResourcesAvailable(); 
	}

	@Override
	public void Fire() throws Exception {
		super.Fire();
		Delay();
		
	}
	
	public void Delay()
	{
		// Seize resources
		typeOfResourcesNeeded.forEach(x -> ResourceManager.GetInstance().SeizeResource(x));
		
		// Seize entity
		
		
		// Set time when process is finished
	}
	
	private void End_Delay()
	{
		// Release resources
		
		// Set next process + Release entity
		
		// Reset time when process is finished

	}

	@Override
	public void On_Tick(Event_Type eventType) {
		End_Delay();
		
	}

	@Override
	public void SetNextSequenceObjectForEntity(Entity entity) {
		// TODO Auto-generated method stub
		
	}


}
