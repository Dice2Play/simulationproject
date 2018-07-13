package simulation.process.commands;

import simulation.interfaces.Command;
import simulation.process.Process;

public class ReleaseProcessCommand implements Command{

	Process currentProcess;
	
	
	public ReleaseProcessCommand(Process process)
	{
		this.currentProcess = process;
	}
	
	@Override
	public void Execute() {
		
		// Release 
		currentProcess.Release();
		
		
		
		// Update processing time for entity
		currentProcess.GetNextEntityFromQueue().UpdateProcessingTime(currentProcess.GetProcessTime());
		
		// Set next process + Release entity
		currentProcess.SetNextSequenceObjectForEntity();
		
		// Set process available again for further firing
		currentProcess.SetIsAvailable(true);
		currentProcess.SetCurrentEntityToNull();
		
	}

	
	
}
