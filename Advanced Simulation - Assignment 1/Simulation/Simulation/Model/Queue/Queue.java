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
		double propabilities_k_groups_in_time_slot[] = new double[] {0.2, 0.6, 0.2}; /* 0, 1, 2 GROUPS */
		double propabilities_m_persons_in_group[] = new double[] {0.2, 0.2, 0.2, 0.2, 0.2}; /* 1, 2, 3, 4, 5 PERSONS */
				
		int nofGroups = Probability.Probability.generate_random_event(
				propabilities_k_groups_in_time_slot);

		for(int i = 0; i < nofGroups; i++) {
			int nofPersons;

			if(maxGroupSize > 1) {
				nofPersons = Probability.Probability.generate_random_event(
						propabilities_m_persons_in_group) + 1;  /* index 0, means 1 person */
			} else {
				nofPersons = 1;
			}
			groupsInQueue.add(new QueueObject(nofPersons, queueID));
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
