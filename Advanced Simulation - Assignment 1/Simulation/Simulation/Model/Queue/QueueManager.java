package Simulation.Model.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


import Simulation.Enums.Queue_Priority;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Resource.Resource;
import Simulation.Model.Time.TimeManager;
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

	// Returns amount of spots seized
	public static int SeizeQueueObject(int seizeTime, int capacity ) 
	{
		int amountOfSpotsTaken = 0;
		
		try
		{
			
			while(amountOfSpotsTaken < capacity)
			{
				// Get group-queue queue
				Queue groupQueue = GetQueueByPriority(Queue_Priority.High);
				
				// Get single rider queue
				Queue singleQueue = GetQueueByPriority(Queue_Priority.Low);
				
				// Check if group-queue can be fit
				while(groupQueue.HasNextQueueObject())
				{
					//Check if fits, otherwise break loop
					if((amountOfSpotsTaken + groupQueue.GroupSizeNextQueueObject() < capacity))
					{
						int queueObjectSpots = groupQueue.GroupSizeNextQueueObject();
						
						groupQueue.SeizeFirstQueueObject(seizeTime);
						amountOfSpotsTaken = amountOfSpotsTaken + queueObjectSpots; 
					}
					
					else break;
				}
				
				
				// Check if single rider can be fit
				while(singleQueue.HasNextQueueObject())
				{
					//Check if fits, otherwise break loop
					if((amountOfSpotsTaken + singleQueue.GroupSizeNextQueueObject() < capacity))
					{
						int queueObjectSpots = singleQueue.GroupSizeNextQueueObject();
						
						singleQueue.SeizeFirstQueueObject(seizeTime);
						amountOfSpotsTaken = amountOfSpotsTaken + queueObjectSpots; 
					}
					
					else break;
				}
				
				
								
				// Finally return amount of spots taken
				return amountOfSpotsTaken;
			}
		
		}
		
		catch(Exception ex) { System.out.println(ex.getMessage()); }
	
		
		// if no spots could be filled
		return 0;
			
	}
	
	private static Queue GetQueueByPriority(Queue_Priority priority) throws Exception
	{
		for(Queue queue : queues)
		{
			if(queue.GetQueuePriority() == priority)
			{
				return queue;
			}
		}
		
		// If no 
		throw new Exception("No queue with given criteria could be found.");
	}
	
	public static boolean CheckIfAnyQueueObjectCanBeReleased()
	{
		for(Queue q : queues)
		{
			if(q.CanRelease())
			{
				return true;
			}
		}
		
		// If no resources can be released
		return false;
	}
	
	public static void ReleaseQueueObjects()
	{
		for(Queue q : queues)
		{
			if(q.CanRelease())
			{
				q.Release();
			}
		}
	}


		
}

