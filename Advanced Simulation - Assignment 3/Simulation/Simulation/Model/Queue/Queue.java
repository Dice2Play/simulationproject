package Simulation.Model.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import Simulation.Enums.Queue_Priority;
import Simulation.Interfaces.IQueueGenerateBehavior;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Time.TimeManager;
import Statistics.ArtificialDistribution;

public abstract class Queue implements Tick_Listener {

	private final Queue_Priority queueingPriority;
	protected final String queueID;
	protected IQueueGenerateBehavior generateQueueBehavior;
	protected LinkedList<QueueObject> queueObjects = new LinkedList<QueueObject>();
	
	
	public Queue(Queue_Priority queueingPriority, String queueID)
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
		queueObjects.remove(firstQueueObject);		
	}
	
	Queue_Priority GetQueuePriority()
	{
		return queueingPriority;
	}
	
	// Return size of NextQueueObject
	int GroupSizeNextQueueObject()
	{
		return queueObjects.getFirst().GetGroupSize();
	}
	
	// Retrieve first QueueObject
	QueueObject FirstQueueObject()
	{
		return queueObjects.getFirst();
	}
	
	// Return if queue has another queueobject
	boolean HasNextQueueObject()
	{
		return !queueObjects.isEmpty();
	}
	
	void GenerateQueueObjects()
	{
		generateQueueBehavior.GenerateQueueObjects();
	}
	
	
	String GetID()
	{
		return queueID;
	}

	@Override
	public void Event_Tick(double timePassed)
	{
		// Generate queue objects according to their behavior
		GenerateQueueObjects();	
	}
	

	LinkedList<QueueObject> GetQueueObjectList()
	{
		return queueObjects;
	}
	
	
}
