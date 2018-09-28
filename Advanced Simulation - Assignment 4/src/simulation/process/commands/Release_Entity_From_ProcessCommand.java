package simulation.process.commands;

import simulation.entity.Entity;
import simulation.interfaces.Command;
import simulation.process.Process;

public class Release_Entity_From_ProcessCommand implements Command{

	Process currentProcess;
	
	
	public Release_Entity_From_ProcessCommand(Process process)
	{
		this.currentProcess = process;
	}
	
	@Override
	public void Execute() {
		
		try
		{
			// Get entity that is reserved by this process
			Entity firstEntityFromQueue = currentProcess.GetReservedEntity();
			
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
