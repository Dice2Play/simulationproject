package Simulation.Model.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import Simulation.Enums.Queue_Priority;
import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Time.TimeManager;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import Statistics.ArtificialDistribution;

public class QueueManager {
	
	private static List<Queue> queues = new ArrayList<Queue>();
		
	public static void AddQueue(Queue queue)
	{
		queues.add(queue);
	}
	
	private static PriorityQueue<Queue> OrderQueuesByPriority()
	{
		Comparator<Queue> comparator = new QueuePriorityComparator();
	    PriorityQueue<Queue> queue = new PriorityQueue<Queue>(comparator);
	    queue.addAll(queues);
		
		return queue;
	}

	// Returns amount of spots seized
	public static int SeizeQueueObject(int seizeTime, int capacity) 
	{
		int amountOfSpotsTaken = 0;
		PriorityQueue<Queue> queuesByPriority = OrderQueuesByPriority();
		
		while(amountOfSpotsTaken <= capacity)
		{
	
			for(Queue queue : queuesByPriority)
			{
			
				while(queue.HasNextQueueObject())
				{
						
					//Check if fits, otherwise break loop
					if((amountOfSpotsTaken + queue.GroupSizeNextQueueObject() <= capacity))
					{
						int queueObjectSpots = queue.GroupSizeNextQueueObject();
						
						queue.SeizeFirstQueueObject(seizeTime);
						amountOfSpotsTaken = amountOfSpotsTaken + queueObjectSpots; 
					}
						
					else break;
								
				}
			}	
				
								
			// Finally return amount of spots taken
			return amountOfSpotsTaken;
		}
		
		// if no spots could be filled
		return 0;
			
	}
		

	public static void Reset() {
		queues = new ArrayList<Queue>();
	}

	public static HashMap<String,Double> GetWaitingTimeArbitraryCustomerPerQueue()
	{
		
		HashMap<String,Double> waitingTimePerQueue = new HashMap<String,Double>();
		
		for(Queue queue : queues)
		{
			// Check if there are any queueObjects, if not return 0.
			if(!queue.HasNextQueueObject()) {waitingTimePerQueue.put(queue.GetID(), 0.0);} // Return 0 if queue has no queueObject
			
			// If it does have any queueObjects, pick a random one
			else
			{
				
				// Get random queueobject
				Random r = new Random();
				QueueObject queueObject = queue.GetQueueObjectList().get(r.nextInt(queue.GetQueueObjectList().size()));
				
				// Add to list
				waitingTimePerQueue.put(queue.GetID(), queueObject.GetWaitingTime());
				
			}
			
			
		}
		
		return waitingTimePerQueue;
		
		
	}
	
	
	public static double GetAverageWaitingTimeCustomer()
	{
		// Check if there are any queueObjects, if not return 0.
		if(CheckIfThereAreAnyQueueObjects() == false) {return 0.0;}
		
		double totalWaitingTime = 0.0;
		double amountOfPeopleInQueue = 0;
		
		for(Queue queue : queues)
		{
			
			
			
			for(QueueObject queueObject : queue.GetQueueObjectList())
			{
				totalWaitingTime = totalWaitingTime + (queueObject.GetWaitingTime() * queueObject.GetGroupSize());
				amountOfPeopleInQueue = amountOfPeopleInQueue + queueObject.GetGroupSize();
				
			}
		}
		
		
		
		return totalWaitingTime/amountOfPeopleInQueue;
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
	
	public static HashMap<String,Double> GetTotalQueueLengthPerQueue()
	{
		HashMap<String,Double> totalQueueLengthPerQueue = new HashMap<String,Double>();
		
		for(Queue queue : queues)
		{
			
			double totalNumberOfPeopleWaiting = 0;
			
			for(QueueObject queueObject : queue.GetQueueObjectList())
			{
				totalNumberOfPeopleWaiting = totalNumberOfPeopleWaiting + queueObject.GetGroupSize();
			}
			
			// Add to list
			totalQueueLengthPerQueue.put(queue.GetID(), totalNumberOfPeopleWaiting);
		}
		
		return totalQueueLengthPerQueue;
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
	
	public static void GenerateQueueObjects()
	{
		// Get queue's
		Queue groupQueue = queues.get(0);		
		
		// Generate amount 
		double[] amountOfPossibleGroups = {0,1,2};
		double[] probabilityAmountOfPossibleGroups = {0.2,0.6,0.2};
			
		int amountOfGroups = (int) Probability.Probability.GetDistributionResult(new ArtificialDistribution(amountOfPossibleGroups, probabilityAmountOfPossibleGroups)); 
						
		// For each group 	
		double[] possibleSizeOfGroups = {1,2,3,4,5};
		double[] probabilityPossibleSizeOfGroups = {0.2,0.2,0.2,0.2,0.2};
		
		if(queues.size()==1) {
			for(int i = 0; i < amountOfGroups; i++)
			{
				int groupSize = (int) Probability.Probability.GetDistributionResult(new ArtificialDistribution(possibleSizeOfGroups, probabilityPossibleSizeOfGroups));
			
				// Add to group queue
				groupQueue.AddQueueObject(new QueueObject(groupSize, groupQueue.GetID()), groupSize);
			
			}
		}
		
		else {
			Queue singleQueue = queues.get(1);
			
			for(int i = 0; i < amountOfGroups; i++)
			{
				int groupSize = (int) Probability.Probability.GetDistributionResult(new ArtificialDistribution(possibleSizeOfGroups, probabilityPossibleSizeOfGroups));
				
				// Add to group queue
				if(groupSize > 1)
				{
					groupQueue.AddQueueObject(new QueueObject(groupSize, groupQueue.GetID()), groupSize);
				}
				
				// Add to single queue
				else
				{
					singleQueue.AddQueueObject(new QueueObject(groupSize, singleQueue.GetID()), groupSize);
				}			
			}
		}
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
	
	private static class QueuePriorityComparator implements Comparator<Queue>
	{

		@Override
		public int compare(Queue queue1, Queue queue2) {
			
			int queueOnePriorityCode = queue1.GetQueuePriority().getLevelCode();
			int queueTwoPriorityCode = queue2.GetQueuePriority().getLevelCode();
			
			
			
			return  queueTwoPriorityCode - queueOnePriorityCode;
		}
		
	}

		
}

