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
	private int counter_x0 = 0;
	private int counter_xg = 0;
	
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
		Process greenLightProcess = new DelayAbleProcess("Green light process", 5, Resource_Type.NONE, 1);
		Process redLightProcess = new NonFireAbleProcess("Red light process", 5, Resource_Type.NONE);
		greenLightProcess.setGreenlight();
		
		ProcessManager.AddProcess(greenLightProcess);
		ProcessManager.AddProcess(redLightProcess);
				
	

	}
	//per run
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
	      ResultManager.setCounterg(counter_xg);
	      ResultManager.setCounter0(counter_x0);
		
	}
	

	@Override
	public void Event_Tick(double timePassed) 
	{
		if(ResourceManager.CheckIfAnyResourceCanBeReleased()) { ResourceManager.ReleaseResources();}
		if(ProcessManager.CheckIfCurrentProcessCanFinish()) {ProcessManager.FinishCurrentProcess();}
		
	}

	public void Reset() {
		QueueManager.Reset();
		ResourceManager.Reset();
		TimeManager.Reset();
		ProcessManager.Reset();
	}
	
	private void Report()
	{
		// Add values to result
		Result result = new Result();
	   result.AddAttribute(new DoubleResultAttribute(QueueManager.GetTotalQueueLength(), "Total amount of people in Queue"));	
	  // Add all queue length at each time unit to list
	   ResultManager.xfullRecord.add((int) QueueManager.GetTotalQueueLength());
	  //with special condition: X0 or Xg
      if(ProcessManager.getCurrentRunningProcess().getGreenlight() == true) {
        if(TimeManager.GetTimeUnitsPassed()% 5 == 0 ||TimeManager.GetTimeUnitsPassed() ==0 )
        {
        	result.AddAttribute(new DoubleResultAttribute(QueueManager.GetTotalQueueLength(), "Start moment queue length with X0:"));
        	counter_x0++;
        	//add X0 queuelength to the list
        	ResultManager.x0Record.add((int) QueueManager.GetTotalQueueLength());
        }
        if((TimeManager.GetTimeUnitsPassed()+1)% 5 == 0)
        {
        	result.AddAttribute(new DoubleResultAttribute(QueueManager.GetTotalQueueLength(), "end moment with queue length Xg:"));	
        	counter_xg++;
        	ResultManager.xgRecord.add((int) QueueManager.GetTotalQueueLength());
        }
      }
		// Add result to resultManager **/

	  ResultManager.AddResults(result);
	}

	@Override
	public TimeManager_Subscriber GetSubscriberType() {
		return timeManagerSubscriberType;
	}
}
