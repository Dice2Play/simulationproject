package Simulation.Model.Queue;

import Simulation.Model.Time.TimeManager;

public class QueueObject{

	private int groupSize;
	private double arrivalTime;
	private double beginSeizeTime;
	private double leaveTime;
	private final String queueID;
	private boolean isSeized;
	 
	
	public QueueObject(int groupSize, String queueID)
	{
		this.groupSize = groupSize;
		this.queueID = queueID;
		this.arrivalTime = TimeManager.GetTimeUnitsPassed();
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
	
	double GetWaitingTime()
	{
		return  TimeManager.GetTimeUnitsPassed() - arrivalTime;
	}

		
	
}
