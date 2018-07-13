package simulation.process;

import simulation.interfaces.Command;

public class Action extends SequenceObject{

	private Command commandToExecute;
	
	public Action(String ID, Command commandToExecute) {
		super(ID, Process_Priority.Normal);
		this.commandToExecute = commandToExecute;
	}
	
	public Command GetCommandToExecute()
	{
		return commandToExecute;
	}

}
