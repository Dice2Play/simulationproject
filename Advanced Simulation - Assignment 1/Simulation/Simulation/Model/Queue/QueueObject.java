package Simulation.Model.Queue;


import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Time.TimeManager;

public class QueueObject{

	private int groupSize;
	private int arrivalTime;
	private int boardingTime;
	private int leaveTime;
	private final String queueID;

	private boolean isSeized;
	 
	
	public QueueObject(int groupSize, String queueID)
	{
		this.groupSize = groupSize;
		this.queueID = queueID;
		this.arrivalTime = TimeManager.GetTimeUnitsPassed();
		this.boardingTime = -1;
		this.leaveTime = -1;
	}

	public String toString()
	{
		String s = String.format("QUEUE_OBJECT { arrival_time: %d, queue_id: %s, group_size: %d",
				arrivalTime, queueID, groupSize);
		if (boardingTime != -1) s += ", boardingTime: " + boardingTime;
		if (leaveTime != -1) s += ", leaveTime: " + leaveTime;
		s += " }";
		return s;
	}
	
	int GetGroupSize()
	{
		return groupSize;
	}
	
	void SetBoardingTime(int boardingTime)
	{
		this.boardingTime =  boardingTime;
	}
	
	String GetQueueID()
	{
		return queueID;
	}
	
	void SetIsSeized(boolean newValue)
	{
		isSeized = newValue;
	}
	
	void SetLeaveTime(int leaveTime)
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
