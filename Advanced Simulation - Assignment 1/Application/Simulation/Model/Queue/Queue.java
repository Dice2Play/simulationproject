package Simulation.Model.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import Simulation.Enums.queue_priority;

public class Queue {

	private queue_priority queueingPriority;
	private LinkedList<QueueObject> groupsInQueue = new LinkedList<QueueObject>();
	private final int maxQueueSize = 8;
	private final int minQueueSize = 1;
	private final int maxQueueLength = 10;
	
	public Queue(queue_priority queueingPriority, int minQueueSize, int maxQueueSize)
	{
		
		this
		this.queueingPriority = queueingPriority;
	}
	
	// Return size of NextQueueObject
	public int GroupSizeNextQueueObject()
	{
		return groupsInQueue.getFirst().GetGroupSize();
	
	}
	
	// Retrieve NextQueueObject
	public QueueObject NextQueueObject()
	{
		return groupsInQueue.getFirst();
	}
	
	private void GenerateQueueObjects()
	{
		Random rand = new Random();
		
		
		
		// Generate QueueObjects till max 
		for(int i = groupsInQueue.size(); i< maxQueueLength; i++)
		{
			// Generate random number between 1 and 8
			groupsInQueue.add(new QueueObject(rand.nextInt(maxQueueSize) + minQueueSize));
		}
	}
	
	 
	
	
}
