package simulation.process.behavior;

import simulation.entity.Entity;
import simulation.interfaces.Command;

public class ActionFire extends FireBehavior {

	private simulation.process.Action action; 
	
	public ActionFire(simulation.process.Action action)
	{
		this.action = action;
	}
	
	
	@Override
	public void Fire() {
		try
		{
			// Get first entity from queue
			Entity firstEntityFromQueue = action.GetFirstEntityFromQueue();
			
					
			action.GetCommandToExecute().Execute();
			action.GetNextSequenceBehavior().SetNextSequenceObjectForEntity(firstEntityFromQueue);
			action.RemoveFirstEntityFromQueue();
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	

}
