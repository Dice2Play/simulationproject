package Simulation.Model.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import Simulation.Enums.Queue_Priority;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Time.TimeManager;
import Statistics.ArtificialDistribution;

public abstract class Queue implements Tick_Listener {

	private final Queue_Priority queueingPriority;
	private final String queueID;
	
	
	private LinkedList<QueueObject> groupsInQueue = new LinkedList<QueueObject>();
	
	public Queue(Queue_Priority queueingPriority, int minGroupSize, int maxGroupSize, String queueID)
	{
		this.queueingPriority = queueingPriority;
		this.queueID = queueID;
		
		// Set listener
		TimeManager.AddTickListener(this);
	}
	
	void SeizeFirstQueueObject(int amountOfTimeToSeize)
	{
		// Get first object
		QueueObject firstQueueObject = FirstQueueObject();
		
		// Seize
		firstQueueObject.SeizeQueueObject(amountOfTimeToSeize);
		
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
	
	abstract void GenerateQueueObjects(); 
	
	void ShowQueueObjectsAdded(int amountOfPeopleAdded)
	{
		System.out.println(queueID + ": Added " + amountOfPeopleAdded + " People");
	}
	
	String GetID()
	{
		return queueID;
	}

	@Override
	public void Event_Tick(int timePassed)
	{
		try {GenerateQueueObjects();}
		catch (Exception e) {e.printStackTrace();}
	}
	

	LinkedList<QueueObject> GetQueueObjectList()
	{
		return groupsInQueue;
	}
	
	
}
