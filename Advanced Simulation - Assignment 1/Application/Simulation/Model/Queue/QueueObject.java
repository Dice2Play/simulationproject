package Simulation.Model.Queue;


import Simulation.Interfaces.Tick_Listener;
import Simulation.Model.Time.TimeManager;

public class QueueObject implements Tick_Listener{

	private int groupSize;
	private int arrivalTime;
	private int boardingTime;
	private int leaveTime;
	private final String queueID;
	private TimeManager timeManager = new TimeManager();
	private boolean isSeized;
	 
	
	public QueueObject(int groupSize, String queueID)
	{
		this.groupSize = groupSize;
		this.queueID = queueID;
		this.arrivalTime = timeManager.GetTimeUnitsPassed();
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
		SetBoardingTime(timeManager.GetTimeUnitsPassed());
		
		// Set leave time
		SetLeaveTime(timeManager.GetTimeUnitsPassed() + amountOfTimeToSeize);
		
		// Set event listener
		timeManager.AddTickListener(this);
	}

	@Override
	public void Event_Tick(int timePassed) {

		if(isSeized)
		{
			// Check if time passed
			if(timePassed > leaveTime)
			{
				// Results
				
				// isSeized back to false
				SetIsSeized(false);
				
				// Remove from tick listener
				timeManager.RemoveTickListener(this);	
			}

		}
		
	}
	
	
	
	
	
	
	
}
