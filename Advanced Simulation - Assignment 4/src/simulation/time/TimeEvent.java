package simulation.time;

import simulation.interfaces.Command;

public class TimeEvent {

	private double timeOnWhichEventOccurs;
	private Command commandToExecute;
	private String description;
	private int dayOnWhichEventIsAvailable;
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
	
	
	
	private void SetTimeAccordingToTimeManagerEndTime()
	{
		if(timeOnWhichEventOccurs > TimeManager.END_TIME)
		{
			double remainder = timeOnWhichEventOccurs - TimeManager.GetInstance().GetEndTime();
			timeOnWhichEventOccurs = remainder;
			
			dayOnWhichEventIsAvailable = TimeManager.GetInstance().GetCurrentDay() + 1;
		}
	}
	
	public boolean IsAvailable()
	{
		// Check if null
		if (dayOnWhichEventIsAvailable == 0) {return true;} 
		else return (TimeManager.GetInstance().GetCurrentDay() >= dayOnWhichEventIsAvailable);
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
