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
	private final String queueID;
	
	
	private LinkedList<QueueObject> groupsInQueue = new LinkedList<QueueObject>();
	
	public Queue(Queue_Priority queueingPriority, int minGroupSize, int maxGroupSize, String queueID)
	{
		this.maxGroupSize = maxGroupSize;
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
	
	void GenerateQueueObjects() throws Exception
	{
		// Check if group queue yes/no
		if(maxGroupSize > 1)
		{
			int amountOfGroups = 0; 
			
			
			boolean[] addGroups = {	Probability.Probability.GetProbability(0.2),
									Probability.Probability.GetProbability(0.6),
									Probability.Probability.GetProbability(0.2)};
			int amountOfGroupsCounter = 0;
			for(boolean addGroup : addGroups)
			{
				if(addGroup) { amountOfGroups = amountOfGroupsCounter; break;}
				else amountOfGroupsCounter = amountOfGroupsCounter + 1;
			}
			
			
			// For each group 	
			for(int i = 0; i < amountOfGroups; i++)
			{
				boolean[] addPersons = {	Probability.Probability.GetProbability(0.2),
											Probability.Probability.GetProbability(0.2),
											Probability.Probability.GetProbability(0.2),
											Probability.Probability.GetProbability(0.2),
											Probability.Probability.GetProbability(0.2)};
				
				int amountOfPersonsCounter = 1;
				
				for(boolean addPerson : addPersons)
				{
						if(addPerson) { groupsInQueue.add(new QueueObject(amountOfPersonsCounter,queueID)); break;}
						else amountOfPersonsCounter = amountOfPersonsCounter + 1;
				}

			}
		}
		
		else
		{
			groupsInQueue.add(new QueueObject(1, queueID));
		}
		

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
