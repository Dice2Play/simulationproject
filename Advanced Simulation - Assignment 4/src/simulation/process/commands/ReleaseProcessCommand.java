package simulation.process.commands;

import simulation.entity.Entity;
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
			// Get first entity from queue
			Entity firstEntityFromQueue = currentProcess.GetFirstEntityFromQueue();		
			
			// Set next process
			currentProcess.GetNextSequenceBehavior().SetNextSequenceObjectForEntity(firstEntityFromQueue);
			
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
