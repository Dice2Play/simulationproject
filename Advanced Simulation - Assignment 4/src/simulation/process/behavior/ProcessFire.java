package simulation.process.behavior;

import simulation.process.commands.Release_Entity_From_ProcessCommand;
import simulation.time.Event_Type;
import simulation.time.TimeEvent;
import simulation.time.TimeManager;

public class ProcessFire extends FireBehavior{

	private simulation.process.Process process;
	
	public ProcessFire(simulation.process.Process process)
	{
		this.process = process;
	}
	
	@Override
	public void Fire() {
		
		// Seize
		process.Seize();
		
		// Set delayed release
		double timeForRelease = TimeManager.GetInstance().GetCurrentTime() + process.GetProcessTime();
		
		// Generate TimeEvent for release
		TimeManager.GetInstance().AddTimeEvent(new TimeEvent(timeForRelease, new Release_Entity_From_ProcessCommand(process), String.format("Process %s has released entity&process", process.GetID()), Event_Type.GENERAL));
		
		
		
	}

}
