package Simulation.Model;

import Simulation.Enums.Queue_Priority;
import Simulation.Enums.Resource_Type;
import  Simulation.Interfaces.*;
import Simulation.Model.Process.ProcessManager;
import Simulation.Model.Queue.Queue;
import Simulation.Model.Queue.QueueManager;
import Simulation.Model.Resource.Boat;
import Simulation.Model.Resource.ResourceManager;
import Simulation.Model.Time.TimeManager;
import Simulation.Interfaces.*;
import Simulation.Model.Resource.*;

public class Model implements Tick_Listener {

	private final TimeManager timeManager = new TimeManager();
	private final QueueManager queueManager = new QueueManager();
	private final ResourceManager resourceManager = new ResourceManager();
	private final ProcessManager processManager = new ProcessManager();
	private final int amountOfTimeUnitsToRun;
	
	private int amountOfTimeUnitsPassed;
	
	public Model(int amountOfTimeUnitsToRun)
	{
		// Set fields
		this.amountOfTimeUnitsToRun = amountOfTimeUnitsToRun;
		
		// Create model objects
		Create();
		
		// Set listeners
		timeManager.AddTickListener(this);
	}
	
	private void Create()
	{
		
		// Create Resources/Queue/Processes
		resourceManager.AddResource(new Boat[] {new Boat("BOAT_1"),
												new Boat("BOAT_2"),
												new Boat("BOAT_3"),
												new Boat("BOAT_4"),
												new Boat("BOAT_5"),
												new Boat("BOAT_6"),
												new Boat("BOAT_7"),
												new Boat("BOAT_8"),
												new Boat("BOAT_9"),
												new Boat("BOAT_10")});
		
		
		queueManager.AddQueue(new Queue[] {		new Queue(Queue_Priority.High, 1,8, "BOAT_GROUP_QUEUE"),
												new Queue(Queue_Priority.Low, 1,1, "BOAT_SINGLE_QUEUE") });
		
		
		processManager.AddProcess(new Simulation.Model.Process.Process[] { new Simulation.Model.Process.Process("Boattrip", 4 , Resource_Type.BOAT)});
		
	}
	
	public void Run()
	{
		while(amountOfTimeUnitsPassed < amountOfTimeUnitsToRun)
		{
			// Print amount of time units passed
			timeManager.PrintAmountOfTimePassed();
			
			// Check if ProcessManager can fire any process
			// If so, fire processes
			while(processManager.CanFire()){ processManager.Fire();}
			
			// increment timeUnit, such that subscribed resourceManager can release resources.
			timeManager.Tick();
			
		}
		
	}
	
	private void SetTimePassed(int timeValue)
	{
		amountOfTimeUnitsPassed = timeValue;	
	}
	

	@Override
	public void Event_Tick(int timePassed) {
		SetTimePassed(timePassed);	
	}
	
	
}
