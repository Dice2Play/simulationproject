package Simulation.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Simulation.Enums.Queue_Priority;
import Simulation.Enums.Resource_Type;
import Simulation.Enums.TimeManager_Subscriber;
import  Simulation.Interfaces.*;
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
	public List<Integer> x0RecordPerRun = new ArrayList<Integer>();
	public List<Integer> xgRecordPerRun = new ArrayList<Integer>();
	public List<Integer> xfullRecordPerRun = new ArrayList<Integer>();
	public static List<Double> delayRecordPerRun = new ArrayList<Double>();
	
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
		QueueManager.AddQueue(new ContinuousQueue(Queue_Priority.High, 1,0.5, "Traffic light queue", "Car"));
		
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
         this.x0RecordPerRun.clear();
         this.xgRecordPerRun.clear();
         this.xfullRecordPerRun.clear();
         delayRecordPerRun.clear();
		
		while(TimeManager.GetTimeUnitsPassed() <= amountOfTimeUnitsToRun)
		{
			// Check if ProcessManager can fire any process
			// If so, fire processes
			try {if(ProcessManager.CanFire()){ ProcessManager.Fire();}}
			catch (Exception e) {e.printStackTrace();}
		
			// Save results from current timeUnit
			Report();
			//enableVechicleAcuated(false);
			// increment timeUnit, such that:
			// - subscribed resources can release resources.
			// - subscribed queue's can generate new customers.
			TimeManager.Tick();
			
		}
	      ResultManager.setCounterg(counter_xg);
	      ResultManager.setCounter0(counter_x0);
	  //calculate avg per run for each value    
	 double avg1=this.x0RecordPerRun.stream().mapToDouble(val -> val).average().orElse(0);
	 double avg2=this.xgRecordPerRun.stream().mapToDouble(val -> val).average().orElse(0);
	 double avg3=this.xfullRecordPerRun.stream().mapToDouble(val -> val).average().orElse(0);
	 double avg4=delayRecordPerRun.stream().mapToDouble(val -> val).average().orElse(0);
	 //add avg to result manager
	 ResultManager.xgRecordAvgPerRun.add(avg2);
	 ResultManager.x0RecordAvgPerRun.add(avg1);
	 ResultManager.xfullRecordAvgPerRun.add(avg3);
	 ResultManager.delayAvgPerRun.add(avg4);
	}


	@Override
	public void Event_Tick(double timePassed) 
	{
		if(ResourceManager.CheckIfAnyResourceCanBeReleased()) { ResourceManager.ReleaseResources();}
		//defalut:
	//	if(ProcessManager.CheckIfCurrentProcessCanFinish()) {ProcessManager.FinishCurrentProcess();}
		//test question 3 must enable the fellow line
		if(ProcessManager.CheckIfCurrentProcessCanFinish2()) {ProcessManager.FinishCurrentProcess();}
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
	   this.xfullRecordPerRun.add((int) QueueManager.GetTotalQueueLength());//for variance
	  //with special condition: X0 or Xg
      if(ProcessManager.getCurrentRunningProcess().getGreenlight() == true) {
        if(TimeManager.GetTimeUnitsPassed()% 5 == 0 ||TimeManager.GetTimeUnitsPassed() ==0 )
        {
        	result.AddAttribute(new DoubleResultAttribute(QueueManager.GetTotalQueueLength(), "Start moment queue length with X0:"));
        	counter_x0++;
        	//add X0 queuelength to the list
        	ResultManager.x0Record.add((int) QueueManager.GetTotalQueueLength());
        	this.x0RecordPerRun.add((int) QueueManager.GetTotalQueueLength());
        }
        if((TimeManager.GetTimeUnitsPassed()+1)% 5 == 0)
        {
        	result.AddAttribute(new DoubleResultAttribute(QueueManager.GetTotalQueueLength(), "end moment with queue length Xg:"));	
        	counter_xg++;
        	ResultManager.xgRecord.add((int) QueueManager.GetTotalQueueLength());
        	this.xgRecordPerRun.add((int) QueueManager.GetTotalQueueLength());
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
