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
		
		try
		{
			// Set next process
			currentProcess.GetNextSequenceBehavior().SetNextSequenceObjectForEntity();
			
			// Remove entity from current queue
			currentProcess.RemoveFirstEntityFromQueue();
			
			// Set process available again for further firing
			currentProcess.Release();
			
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
				
	}

	
	
}
