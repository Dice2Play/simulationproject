package simulation.process;

import simulation.interfaces.Command;

public class ReleaseProcessCommand implements Command{

	Process currentProcess;
	
	
	public ReleaseProcessCommand(Process process)
	{
		this.currentProcess = process;
	}
	
	@Override
	public void Execute() {
		
		// Release resources
		currentProcess.GetSeizedResources().forEach(x -> x.Release());
		
		// Update processing time for entity
		currentProcess.GetCurrentEntity().UpdateProcessingTime(currentProcess.GetProcessTime());
		
		// Set next process + Release entity
		currentProcess.SetNextSequenceObjectForEntity();
		
		// Set process available again for further firing
		currentProcess.SetIsAvailable(true);
		currentProcess.SetCurrentEntityToNull();
		
	}

	
	
}
