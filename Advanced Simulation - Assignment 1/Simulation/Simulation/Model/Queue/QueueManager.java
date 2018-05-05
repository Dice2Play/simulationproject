package Simulation.Model.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import Simulation.Enums.Queue_Priority;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Time.TimeManager;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class QueueManager {
	
	private static List<Queue> queues = new ArrayList<Queue>();
		
	public static void AddQueue(Queue queue)
	{
		queues.add(queue);
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
	

	public static void Reset() {
		queues = new ArrayList<Queue>();
	}

	
	public static double GetWaitingTimeArbitraryCustomer()
	{
		// Check if there are any queueObjects, if not return 0.
		if(CheckIfThereAreAnyQueueObjects() == false) {return 0.0;}
		
		Random r = new Random();
		
		// Get a random queue
		boolean hasValidQueue = false;
		Queue queue = null;
		
		// Could be that it picks an empty queue
		while(!hasValidQueue)
		{
			queue = queues.get(r.nextInt(queues.size()));
			
			if(queue.HasNextQueueObject()) { hasValidQueue = true;}
		}
		
		// Get a random queueobject
		QueueObject queueObject = queue.GetQueueObjectList().get(r.nextInt(queue.GetQueueObjectList().size()));
		
		// Get waiting time
		return queueObject.GetWaitingTime();
	}
	
	public static double GetTotalQueueLength()
	{
		double totalNumberOfPeopleWaiting = 0;
		
		for(Queue queue : queues)
		{
			for(QueueObject queueObject : queue.GetQueueObjectList())
			{
				totalNumberOfPeopleWaiting = totalNumberOfPeopleWaiting + queueObject.GetGroupSize();
			}
		}
		
		return totalNumberOfPeopleWaiting;
		
	}
	
	private static boolean CheckIfThereAreAnyQueueObjects()
	{
		for(Queue queue : queues)
		{
			if(queue.HasNextQueueObject()) { return true;}
		}
		
		// Default value
		return false;
		
	}

		
}

