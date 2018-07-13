package simulation.process;


import simulation.interfaces.BooleanCommand;
import simulation.process.behavior.NextSequenceBasedOnCondition;

public class DecisionBasedOnCondition extends Decision{

	private boolean condition;
	private boolean hasBooleanCommandFireAlready;
	private final BooleanCommand boolCommand;
	
	public DecisionBasedOnCondition(String ID, BooleanCommand boolCommand) {
		super(ID);
		setNextSequenceBehavior(new NextSequenceBasedOnCondition(this));
		this.boolCommand = boolCommand;
	}
	
	public boolean GetConditionResult()
	{
		if(hasBooleanCommandFireAlready == false)
		{
			condition = boolCommand.Execute();
			hasBooleanCommandFireAlready = true;
		}
		
		return condition;
	}

}
