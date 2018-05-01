package Simulation.Model.Queue;


import Simulation.Model.Time.TimeManager;

public class QueueObject {

	private int groupSize;
	private int arrivalTime;
	private int boardingTime;
	private TimeManager timeManager = new TimeManager();
	 
	
	public QueueObject(int groupSize)
	{
		this.groupSize = groupSize;
		this.arrivalTime = timeManager.AmountOfTimePassed();
	}
	
	public int GetGroupSize()
	{
		return groupSize;
	}
	
	public void SetBoardingTime(int boardingTime)
	{
		this.boardingTime =  boardingTime;
	}
	
	
	
	
	
}
