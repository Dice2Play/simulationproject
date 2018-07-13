package simulation.process.behavior;

import simulation.interfaces.Command;

public class ActionFire extends FireBehavior {

	private simulation.process.Action action; 
	
	public ActionFire(simulation.process.Action action)
	{
		this.action = action;
	}
	
	
	@Override
	public void Fire() {
		action.GetCommandToExecute().Execute();
		action.GetNextSequenceBehavior().SetNextSequenceObjectForEntity();
		action.RemoveFirstEntityFromQueue();
	}
	
	

}
