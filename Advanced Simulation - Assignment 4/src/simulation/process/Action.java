package simulation.process;

import simulation.interfaces.Command;
import simulation.process.behavior.ActionFire;
import simulation.process.behavior.CanFireEntity;
import simulation.process.behavior.DecisionFire;
import simulation.process.behavior.NextSequenceBasedOnChance;
import simulation.process.behavior.RegularNextSequence;

public class Action extends SequenceObject{

	private Command commandToExecute;
	
	public Action(String ID, Command commandToExecute) {
		super(ID, Process_Priority.Normal);
		nextSequenceBehavior = new RegularNextSequence(this);
		canFireBehavior = new CanFireEntity(this);
		fireBehavior = new ActionFire(this);
		this.commandToExecute = commandToExecute;
	}
	
	public Command GetCommandToExecute()
	{
		return commandToExecute;
	}

	@Override
	public void Validate() throws Exception {
		if(GetLinkedSequenceObjects().size() != 1){throw new Exception(String.format("VALIDATE MODEL ERROR: Exactly 1 Sequence Link need to be set for %s", this.GetID()));}
		if(GetQueue() == null) {throw new Exception(String.format("VALIDATE MODEL ERROR: No Queue has been set for %s", this.GetID()));}
		
	}

}
