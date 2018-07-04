package simulation.time;

import simulation.interfaces.Command;

public class TimeEvent {

	private double timeOnWhichEventOccurs;
	private Command commandToExecute;
	private String description;
	
	public TimeEvent(double timeOnWhichEventOccurs, String description)
	{
		this.timeOnWhichEventOccurs = timeOnWhichEventOccurs;
		this.description = description;		
	}
	
	public TimeEvent(double timeOnWhichEventOccurs, Command commandToExecute, String description)
	{
		this.timeOnWhichEventOccurs = timeOnWhichEventOccurs;
		this.commandToExecute = commandToExecute;
		this.description = description;
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
