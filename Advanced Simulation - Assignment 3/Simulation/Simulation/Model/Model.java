package Simulation.Model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Simulation.Enums.Queue_Priority;
import Simulation.Enums.Resource_Type;
import Simulation.Enums.TimeManager_Subscriber;
import  Simulation.Interfaces.*;
import Simulation.Model.Queue.QueueManager;
import Simulation.Model.Resource.ResourceManager;
import Simulation.Model.Time.TimeManager;
import Simulation.Results.DoubleResultAttribute;
import Simulation.Results.Result;
import Simulation.Results.ResultManager;
import Simulation.Model.Process.*;
import Simulation.Model.Process.Process;
import Simulation.Model.Queue.*;
import Simulation.Interfaces.*;
import Simulation.Model.Resource.*;

public class Model implements Tick_Listener {

	private final int amountOfTimeUnitsToRun;
	private TimeManager_Subscriber timeManagerSubscriberType = TimeManager_Subscriber.MODEL;
	
	
	public Model(int amountOfTimeUnitsToRun)
	{
		// Set fields
		this.amountOfTimeUnitsToRun = amountOfTimeUnitsToRun;
		
		// Create model objects
		Create();
		
		// Set listeners
		TimeManager.AddTickListener(this);

	}
	
	// Create Resources/Queue/Processes
	private void Create()
	{
		// Resources
		
		// Queue's
		QueueManager.AddQueue(new ContinuousQueue(Queue_Priority.High, 1,1, "Traffic light queue", "Car"));
		
		// Processes
		Process greenLightProcess = new DelayAbleProcess("Green light process", 8, Resource_Type.NONE, 1);
		Process redLightProcess = new NonFireAbleProcess("Red light process", 12, Resource_Type.NONE);
		
		ProcessManager.AddProcess(greenLightProcess);
		ProcessManager.AddProcess(redLightProcess);
				
	

	}
	
	public void Run()
	{
		// Print initial amount of time units passed
		TimeManager.PrintAmountOfTimePassed();
		
		while(TimeManager.GetTimeUnitsPassed() <= amountOfTimeUnitsToRun)
		{
			// Check if ProcessManager can fire any process
			// If so, fire processes
			try {if(ProcessManager.CanFire()){ ProcessManager.Fire();}}
			catch (Exception e) {e.printStackTrace();}
			
			// Save results from current timeUnit
			Report();
			
			// increment timeUnit, such that:
			// - subscribed resources can release resources.
			// - subscribed queue's can generate new customers.
			TimeManager.Tick();
			
		}
		
	}
	

	@Override
	public void Event_Tick(double timePassed) 
	{
		if(ResourceManager.CheckIfAnyResourceCanBeReleased()) { ResourceManager.ReleaseResources();}
	}

	public void Reset() {
		QueueManager.Reset();
		ResourceManager.Reset();
		TimeManager.Reset();
		ProcessManager.Reset();
	}
	
	private void Report()
	{
		// timeManager.getAmountOfEventsInTimePeriod<int>
		
		
	}

	@Override
	public TimeManager_Subscriber GetSubscriberType() {
		return timeManagerSubscriberType;
	}
}
