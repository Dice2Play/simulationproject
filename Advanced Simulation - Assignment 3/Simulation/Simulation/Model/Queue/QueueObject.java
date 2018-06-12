package Simulation.Model.Queue;

import Simulation.Model.Time.TimeManager;

public class QueueObject{

	private int groupSize;
	private double arrivalTime;
	private double beginSeizeTime;
	private double leaveTime;
	private final String queueID;
	private boolean isSeized;
	 
	
	public QueueObject(int groupSize, String queueID, double arrivalTime)
	{
		this.groupSize = groupSize;
		this.queueID = queueID;
		this.arrivalTime = arrivalTime;
	}
	
	int GetGroupSize()
	{
		return groupSize;
	}
	
	void SetBoardingTime(double boardingTime)
	{
		this.beginSeizeTime =  boardingTime;
	}
	
	String GetQueueID()
	{
		return queueID;
	}
	
	void SetIsSeized(boolean newValue)
	{
		isSeized = newValue;
	}
	
	void SetLeaveTime(double leaveTime)
	{
		this.leaveTime = leaveTime;
	}
	
	void SeizeQueueObject(int amountOfTimeToSeize)
	{
		// Set isSeized to true
		SetIsSeized(true);
		
		// Set boarding time
		SetBoardingTime(TimeManager.GetTimeUnitsPassed());
		
		// Set leave time
		SetLeaveTime(TimeManager.GetTimeUnitsPassed() + amountOfTimeToSeize);
	}
	
	public double GetWaitingTime()
	{
		return  TimeManager.GetTimeUnitsPassed() - arrivalTime;
	}

	public boolean IsAvailable()
	{
		// check if arrivalTime of object has passed
		return (arrivalTime <= TimeManager.GetTimeUnitsPassed());
	}
	
}
