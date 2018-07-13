package simulation.process.commands;

import simulation.entity.EntityManager;
import simulation.interfaces.Command;
import simulation.process.SequenceObject;

public class IncrementAmountOfRejects implements Command {

	
	
	@Override
	public void Execute() {
		EntityManager.GetInstance().IncrementAmountOfRejects();
		
	}

}
