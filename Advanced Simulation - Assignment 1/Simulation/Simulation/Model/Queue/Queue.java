package Simulation.Model.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import Simulation.Enums.Queue_Priority;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Time.TimeManager;

public class Queue implements Tick_Listener {

	private final Queue_Priority queueingPriority;
	private final int maxGroupSize;
	private final int minGroupSize;
	private final int maxQueueLength = 10;
	private final String queueID;
	private final TimeManager timeManager = new TimeManager();
	
	private LinkedList<QueueObject> groupsInQueue = new LinkedList<QueueObject>();
	
	public Queue(Queue_Priority queueingPriority, int minGroupSize, int maxGroupSize, String queueID)
	{
		this.maxGroupSize = maxGroupSize;
		this.minGroupSize = minGroupSize;
		this.queueingPriority = queueingPriority;
		this.queueID = queueID;
		
		// Set listeners
		timeManager.AddTickListener(this);
		
	}
	
	
	public void SeizeFirstQueueObject(int amountOfTimeToSeize)
	{
		// Get first object
		QueueObject firstQueueObject = FirstQueueObject();
		
		// Seize
		firstQueueObject.SeizeQueueObject(amountOfTimeToSeize);
		
		// Remove from list
		groupsInQueue.remove(firstQueueObject);		
	}
	
	public Queue_Priority GetQueuePriority()
	{
		return queueingPriority;
	}
	
	// Return size of NextQueueObject
	public int GroupSizeNextQueueObject()
	{
		return groupsInQueue.getFirst().GetGroupSize();
	}
	
	// Retrieve first QueueObject
	public QueueObject FirstQueueObject()
	{
		return groupsInQueue.getFirst();
	}
	
	private void GenerateQueueObjects()
	{
		Random rand = new Random();
		
		// Check if group queue yes/no
		if(maxGroupSize > 1)
		{
			int[] amountOfGroups = {0,1,2};
			
			for(int amount)
			
			
			
		}
		
		else
		{
			groupsInQueue.add(new QueueObject(1, queueID));
		}
		
		
/*		// Generate QueueObjects till max 
		for(int i = groupsInQueue.size(); i< maxQueueLength; i++)
		{
			if(maxGroupSize > 1)
			{
				// Generate random number between 1 and 8
				groupsInQueue.add(new QueueObject(rand.nextInt(maxGroupSize) + minGroupSize,queueID));
			}
			
			else
			{
				groupsInQueue.add(new QueueObject(1, queueID));
			}
		}*/
	}
	
	public String GetID()
	{
		return queueID;
	}


	@Override
	public void Event_Tick(int timePassed) {
		// TODO Auto-generated method stub
		
	}
	 
	
	
}
