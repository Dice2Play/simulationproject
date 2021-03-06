package Simulation.Model.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import Simulation.Enums.Queue_Priority;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Time.TimeManager;
import Simulation.Results.DoubleResultAttribute;
import Simulation.Results.Result;
import Simulation.Results.ResultManager;
import Statistics.ArtificialDistribution;

public class Queue{

	private final Queue_Priority queueingPriority;
	private final int maxGroupSize;
	private final String queueID;
	private final static List<Double> waitingTime = new ArrayList<Double>();
	
	
	private LinkedList<QueueObject> groupsInQueue = new LinkedList<QueueObject>();
	
	public Queue(Queue_Priority queueingPriority, int minGroupSize, int maxGroupSize, String queueID)
	{
		this.maxGroupSize = maxGroupSize;
		this.queueingPriority = queueingPriority;
		this.queueID = queueID;
	}
	
	void SeizeFirstQueueObject(int amountOfTimeToSeize)
	{
		// Get first object
		QueueObject firstQueueObject = FirstQueueObject();
		
		// Seize
		firstQueueObject.SeizeQueueObject(amountOfTimeToSeize);
		
		// Add result to result manager
		int amountOfPeople = firstQueueObject.GetGroupSize();
		double waitingTime = firstQueueObject.GetWaitingTime();
		Result result = new Result();
		
		for(int index = 0; index < amountOfPeople; index++)
		{
			
			result.AddAttribute(new DoubleResultAttribute(waitingTime, "Waiting Time"));
			result.AddAttribute(new DoubleResultAttribute(waitingTime, queueID + " Waiting Time"));
			
			
		}
		
		
		// Add result to resultManager
		ResultManager.AddResults(result);
		
		
		// Remove from list
		groupsInQueue.remove(firstQueueObject);		
	}
	
	
	Queue_Priority GetQueuePriority()
	{
		return queueingPriority;
	}
	
	// Return size of NextQueueObject
	int GroupSizeNextQueueObject()
	{
		return groupsInQueue.getFirst().GetGroupSize();
	}
	
	// Retrieve first QueueObject
	QueueObject FirstQueueObject()
	{
		return groupsInQueue.getFirst();
	}
	
	// Return if queue has another queuobject
	boolean HasNextQueueObject()
	{
		return !groupsInQueue.isEmpty();
	}

	
	void ShowQueueObjectsAdded(int amountOfPeopleAdded)
	{
		System.out.println(queueID + ": Added " + amountOfPeopleAdded + " People");
	}
	
	String GetID()
	{
		return queueID;
	}
	
	void AddQueueObject(QueueObject queueObject, int amountOfPeopleAdded)
	{
		groupsInQueue.add(queueObject);
		ShowQueueObjectsAdded(amountOfPeopleAdded);
	}

	

	LinkedList<QueueObject> GetQueueObjectList()
	{
		return groupsInQueue;
	}
	  public static List<Double> getWaitingTimeRecord()
	 {
		 return waitingTime;
	 }
	
}
