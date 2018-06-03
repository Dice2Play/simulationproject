package Simulation.Model.Time;

public class TimeEvent {

	private final String eventDescription;
	private final double timeWhichEventOccursOn;
	
	public TimeEvent(String eventDescription, double timeWhichEventOccursOn)
	{
		this.eventDescription = eventDescription;
		this.timeWhichEventOccursOn = timeWhichEventOccursOn;
	}
	
	public String getEventDescription()
	{
		return eventDescription;
	}
	
	public double getTimeWhichEventOccursOn()
	{
		return timeWhichEventOccursOn;
	}
	
}
