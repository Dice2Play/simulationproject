package Simulation.Model.Queue;


import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Time.TimeManager;

class QueueObject{

	private int groupSize;
	private int arrivalTime;
	private int boardingTime;
	private int leaveTime;
	private final String queueID;

	private boolean isSeized;
	 
	
	QueueObject(int groupSize, String queueID)
	{
		this.groupSize = groupSize;
		this.queueID = queueID;
		this.arrivalTime = TimeManager.GetTimeUnitsPassed();
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
	
	boolean CanRelease()
	{
		return TimeManager.GetTimeUnitsPassed() > leaveTime;
	}
	
	void Release()
	{
		// Update statistics
		

	}

		
	
}
