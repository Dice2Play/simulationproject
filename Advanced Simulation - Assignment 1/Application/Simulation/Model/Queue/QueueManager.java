package Simulation.Model.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


import Simulation.Enums.Queue_Priority;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class QueueManager {

	private static List<Queue> queues = new ArrayList<Queue>();
		
	public static void AddQueue(Queue[] queuesToAdd)
	{
		for(Queue q : queuesToAdd)
		{
			queues.add(q);
		}
	}
			
	private static List<Queue> ReturnOrderedQueuesByHighPriority()
	{
		// Create copy
		List<Queue> orderedQueueArrayList = new ArrayList<Queue>();
		queues.forEach(q -> orderedQueueArrayList.add(q));
		
		// Sort 
		orderedQueueArrayList.sort(new SortByQueuePriority());
		
		// Return list
		return orderedQueueArrayList;
	}
	

	// Returns amount of spots seized
	public static int SeizeQueueObject(int seizeTime, int capacity ) 
	{
		int amountOfSpotsTaken = 0;
		while(amountOfSpotsTaken < capacity)
		{
			// Check if group-queue can be fit
			
			// Check if single rider can be fit
		}
		
		
		return amountOfSpotsTaken;
	}
	
	private static Queue[] GetQueueByPriority(Queue_Priority priority)
	{
		List
	}
	

		
}

class SortByQueuePriority implements Comparator<Queue>
{
	@Override
	public int compare(Queue queue_arg0, Queue queue_arg1) {
		return queue_arg0.GetQueuePriority().getLevelCode() - queue_arg1.GetQueuePriority().getLevelCode();
	}
	
}
