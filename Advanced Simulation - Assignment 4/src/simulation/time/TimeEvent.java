package simulation.time;

import simulation.interfaces.Command;

public class TimeEvent {

	private double timeOnWhichEventOccurs;
	private Command commandToExecute;
	private String description;
	
	public TimeEvent(double timeOnWhichEventOccurs, Command commandToExecute, String description)
	{
		this.timeOnWhichEventOccurs = timeOnWhichEventOccurs;
		this.commandToExecute = commandToExecute;
		this.description = description;
	}
	
	public void ExecuteTimeEvent()
	{
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
