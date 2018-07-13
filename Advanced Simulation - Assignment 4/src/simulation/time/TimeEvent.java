package simulation.time;

import simulation.interfaces.Command;

public class TimeEvent {

	private double timeOnWhichEventOccurs;
	private Command commandToExecute;
	private String description;
	private int dayOnWhichEventIsAvailable = Integer.MAX_VALUE;
	private Event_Type eventType;
	
	public TimeEvent(double timeOnWhichEventOccurs, String description, Event_Type eventType)
	{
		this.timeOnWhichEventOccurs = timeOnWhichEventOccurs;
		this.description = description;		
		this.eventType = eventType;
		
		
		SetTimeAccordingToTimeManagerEndTime();
	}
	
	public TimeEvent(double timeOnWhichEventOccurs, Command commandToExecute, String description, Event_Type eventType)
	{
		this.timeOnWhichEventOccurs = timeOnWhichEventOccurs;
		this.commandToExecute = commandToExecute;
		this.description = description;
		this.eventType = eventType;
		
		SetTimeAccordingToTimeManagerEndTime();
	}
	
	public Event_Type GetTypeOfEvent()
	{
		return eventType;
	}
	
	/**
	 * If eventType is Generate (=Customer arrives), then use closing time for customers as endTime,
	 * otherwise use endTime for all.
	 * @throws Exception 
	 */
	private double GetEndTimeToUse() throws Exception
	{
		switch(eventType)
		{
			case GENERAL:
			return TimeManager.GetInstance().GetEndTimeAll();
		
		case GENERATE:
			return TimeManager.GetInstance().GetEndTimeCustomersAndCleaners();
			
		default:
				throw new Exception("Shouldnt occur.");
				
		}
		
		
		
	}
	
	private void SetTimeAccordingToTimeManagerEndTime()
	{
	
		try 
		{
	
			double 	EndTimeToUse = GetEndTimeToUse();
			
			if(timeOnWhichEventOccurs > EndTimeToUse || ((timeOnWhichEventOccurs >= EndTimeToUse) && (eventType == Event_Type.GENERATE)))
			{
				double remainder = timeOnWhichEventOccurs - EndTimeToUse;
				timeOnWhichEventOccurs = remainder;
				
				dayOnWhichEventIsAvailable = TimeManager.GetInstance().GetCurrentDay() + 1;
			}
			
			else
			{
				dayOnWhichEventIsAvailable = TimeManager.GetInstance().GetCurrentDay();
			}
				
		} 
		
		catch (Exception e) {	e.printStackTrace();}
		
		
	}
	
	public boolean IsAvailable()
	{

		return (TimeManager.GetInstance().GetCurrentDay() >= dayOnWhichEventIsAvailable);
	}
	
	public void ExecuteTimeEvent()
	{
		if(commandToExecute == null) {return;}
		commandToExecute.Execute();
	}
	
	public double GetTimeOnWhichEventOccurs()
	{
		return timeOnWhichEventOccurs;
	}
	
	public String GetDescription()
	{
		return description;
	}
}
