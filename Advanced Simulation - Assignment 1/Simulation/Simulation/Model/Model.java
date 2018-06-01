package Simulation.Model;

import Simulation.Enums.Queue_Priority;
import Simulation.Enums.Resource_Type;
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
		ResourceManager.AddResource(new Boat("BOAT_1"));

		
		// Queue's
		QueueManager.AddQueue(new Queue(Queue_Priority.High, 1,8, "BOAT_GROUP_QUEUE"));
		//QueueManager.AddQueue(new Queue(Queue_Priority.Low, 1,1, "BOAT_SINGLE_QUEUE"));
		
		// Processes
		ProcessManager.AddProcess(new Process("Boattrip", 1 , Resource_Type.BOAT));
	

	}
	
	public void Run()
	{
		while(amountOfTimeUnitsPassed < amountOfTimeUnitsToRun)
		{
			// Print amount of time units passed
			TimeManager.PrintAmountOfTimePassed();
			

			
			// Check if ProcessManager can fire any process
			// If so, fire processes
			if(ProcessManager.CanFire()){ ProcessManager.Fire();}
			
			// Save results from previous timeUnit
			Report();
			
			// increment timeUnit, such that:
			// - subscribed resourceManager can release resources.
			// - subscribed queueManager can generate new customers.
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
	
	}

	public void Reset() {
		QueueManager.Reset();
		ResourceManager.Reset();
		TimeManager.Reset();
		ProcessManager.Reset();
	}
	
	private void Report()
	{

		// Retrieve values
		double meanBoatOccupancy = ResourceManager.GetResourceOccupancy();
		double waitingTimeArbitraryCustomer = QueueManager.GetWaitingTimeArbitraryCustomer();
		double totalQueuelength =  QueueManager.GetTotalQueueLength(); // Total number of people waiting
			
		// Add values to result
		Result result = new Result();
		result.AddAttribute(new DoubleResultAttribute(meanBoatOccupancy, "Mean boat occupancy"));
		result.AddAttribute(new DoubleResultAttribute(waitingTimeArbitraryCustomer, "Waiting Time arbitrary customer"));
		result.AddAttribute(new DoubleResultAttribute(totalQueuelength, "Total Queue Length"));
			
			
			
		// Add result to resultManager
		ResultManager.AddResults(result);
		
	}
}
