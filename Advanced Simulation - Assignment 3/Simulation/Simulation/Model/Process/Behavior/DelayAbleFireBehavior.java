package Simulation.Model.Process.Behavior;

import Simulation.Enums.Resource_Type;
import Simulation.Enums.TimeManager_Subscriber;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Queue.QueueManager;
import Simulation.Model.Resource.ResourceManager;
import Simulation.Model.Time.TimeManager;

public class DelayAbleFireBehavior implements IProcessFireBehavior, Tick_Listener {

	
	private double delayTime;
	private double startDelayTime;
	private double endDelayTime;
	private boolean isDelayed; // if delayed, process can't fire
	private int processTime;
	private Resource_Type resourceTypeNeeded;
	protected TimeManager_Subscriber timeManagerSubscriberType = TimeManager_Subscriber.PROCESS;
	
	public DelayAbleFireBehavior(double delayTime, int processTime, Resource_Type resourceTypeNeeded)
	{
		this.delayTime = delayTime;
		this.processTime = processTime;
		this.resourceTypeNeeded = resourceTypeNeeded;

		// Set listener
		TimeManager.AddTickListener(this);
	}
	

	@Override
	public void Fire() {
		
		// Fire process
		QueueManager.SeizeQueueObject(processTime, ResourceManager.GetCapacityOfResource(resourceTypeNeeded));
		Delay();
		
	}

	@Override
	public boolean CanFire() {
		if(isDelayed()) { return false;}
		
		return QueueManager.CheckIfThereAreAnyQueueObjectsAvailable();

		
	}

	private void Delay()
	{
		startDelayTime = TimeManager.GetTimeUnitsPassed();
		endDelayTime = startDelayTime + delayTime;
		isDelayed = true;
		
	}
	
	private boolean isDelayed()
	{
		return isDelayed;
	}
	
	private void unDelay()
	{
		isDelayed = false;
	}
	
	

	@Override
	public void Event_Tick(double timeUnitsPassed) {
		
		if(TimeManager.GetTimeUnitsPassed() >= endDelayTime)
		{
			unDelay();
		}
		
		
	}


	@Override
	public TimeManager_Subscriber GetSubscriberType() {
		return timeManagerSubscriberType;
	}
	

	


	

	
}
