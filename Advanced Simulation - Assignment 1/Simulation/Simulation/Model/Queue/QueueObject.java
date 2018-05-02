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
	}
	
	public int GetGroupSize()
	{
		return groupSize;
	}
	
	public void SetBoardingTime(int boardingTime)
	{
		this.boardingTime =  boardingTime;
	}
	
	public String GetQueueID()
	{
		return queueID;
	}
	
	private void SetIsSeized(boolean newValue)
	{
		isSeized = newValue;
	}
	
	private void SetLeaveTime(int leaveTime)
	{
		this.leaveTime = leaveTime;
	}
	
	public void SeizeQueueObject(int amountOfTimeToSeize)
	{
		// Set isSeized to true
		SetIsSeized(true);
		
		// Set boarding time
		SetBoardingTime(TimeManager.GetTimeUnitsPassed());
		
		// Set leave time
		SetLeaveTime(TimeManager.GetTimeUnitsPassed() + amountOfTimeToSeize);
	}
	
	public boolean CanRelease()
	{
		return TimeManager.GetTimeUnitsPassed() > leaveTime;
	}
	
	public void Release()
	{
		// Update statistics
		

	}

		
	
}
