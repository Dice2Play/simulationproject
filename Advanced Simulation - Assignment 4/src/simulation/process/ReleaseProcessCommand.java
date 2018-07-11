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
		currentProcess.seizedResources.forEach(x -> x.Release());
		
		// Set next process + Release entity
		currentProcess.SetNextSequenceObjectForEntity();
		
		// Set process available again for further firing
		currentProcess.SetIsAvailable(true);
		currentProcess.SetCurrentEntityToNull();
		
	}

	
	
}
