package Simulation.Model.Queue;

import Simulation.Enums.queue_priority;

public class QueueObject {

	private int groupSize;
	private int arrivalTime;
	private int boardingTime;
	 
	
	public QueueObject(int groupSize)
	{
		this.groupSize = groupSize;
	}
	
	public int GetGroupSize()
	{
		return groupSize;
	}
	
	
	
}
