package Simulation.Model.Queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


import Simulation.Enums.Queue_Priority;

public class Queue {

	private Queue_Priority queueingPriority;
	private LinkedList<QueueObject> groupsInQueue = new LinkedList<QueueObject>();
	private int maxGroupSize;
	private int minGroupSize;
	private final int maxQueueLength = 10;
	
	public Queue(Queue_Priority queueingPriority, int minGroupSize, int maxGroupSize)
	{
		this.maxGroupSize = maxGroupSize;
		this.minGroupSize = minGroupSize;
		this.queueingPriority = queueingPriority;
		
		// Generate queue
		GenerateQueueObjects();
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

		// Generate QueueObjects till max 
		for(int i = groupsInQueue.size(); i< maxQueueLength; i++)
		{
			if(maxGroupSize > 1)
			{
				// Generate random number between 1 and 8
				groupsInQueue.add(new QueueObject(rand.nextInt(maxGroupSize) + minGroupSize));
			}
			
			else
			{
				groupsInQueue.add(new QueueObject(1));
			}
		}
	}
	
	 
	
	
}
