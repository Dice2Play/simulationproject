package Simulation.Model;

import Simulation.Enums.Queue_Priority;
import Simulation.Enums.Resource_Type;
import  Simulation.Interfaces.*;
import Simulation.Model.Process.ProcessManager;
import Simulation.Model.Queue.QueueManager;
import Simulation.Model.Resource.ResourceManager;
import Simulation.Model.Time.TimeManager;
import Simulation.Results.Result;
import Simulation.Results.ResultManager;
import Simulation.Interfaces.*;
import Simulation.Model.Resource.*;

public class Model implements Tick_Listener {

	private final int amountOfTimeUnitsToRun;
	
	private int amountOfTimeUnitsPassed;
	
	public Model(int amountOfTimeUnitsToRun)
	{
		// Set fields
		this.amountOfTimeUnitsToRun = amountOfTimeUnitsToRun;
		
		// Create model objects
		Create();
		
		// Set listeners
		TimeManager.AddTickListener(this);

	}
	
	private void Create()
	{
		
		// Create Resources/Queue/Processes
		// Resources
		ResourceManager.AddResource(Resource_Type.BOAT, "BOAT_1");
		ResourceManager.AddResource(Resource_Type.BOAT, "BOAT_2");
		
		// Queue's
		QueueManager.AddQueue(Queue_Priority.High, 1,8, "BOAT_GROUP_QUEUE");
		QueueManager.AddQueue(Queue_Priority.Low, 1,1, "BOAT_SINGLE_QUEUE");
		
		// Processes
		ProcessManager.AddProcess("Boattrip", 4 , Resource_Type.BOAT);
		
	}
	
	public void Run()
	{
		while(amountOfTimeUnitsPassed < amountOfTimeUnitsToRun)
		{
			// Print amount of time units passed
			TimeManager.PrintAmountOfTimePassed();
			
			// Save results from previous timeUnit
			Report();
			
			// Check if ProcessManager can fire any process
			// If so, fire processes
			if(ProcessManager.CanFire()){ ProcessManager.Fire();}
			
			// increment timeUnit, such that subscribed resourceManager can release resources.
			TimeManager.Tick();
			
		}
		
	}
	
	private void SetTimePassed(int timeValue)
	{
		amountOfTimeUnitsPassed = timeValue;	
	}
	

	@Override
	public void Event_Tick(int timePassed) {
		SetTimePassed(timePassed);	
		
		if(ResourceManager.CheckIfAnyResourceCanBeReleased()) { ResourceManager.ReleaseResources();}
		if(QueueManager.CheckIfAnyQueueObjectCanBeReleased()) {QueueManager.ReleaseQueueObjects();}
	}

	public void Reset() {
		QueueManager.Reset();
		ResourceManager.Reset();
		TimeManager.Reset();
		ProcessManager.Reset();
	}
	
	private void Report()
	{
		// Nothing of interest at timeUnit 0, so skip.
		if(amountOfTimeUnitsPassed > 0)
		ResultManager.AddResults(new Result(2,2,2.0));
		
		// QueueManager getWaitingTime etc in result
		
	}
}
