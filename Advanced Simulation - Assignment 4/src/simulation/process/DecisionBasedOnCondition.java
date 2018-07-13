package simulation.process;


import simulation.interfaces.BooleanCommand;
import simulation.process.behavior.CanFireEntity;
import simulation.process.behavior.DecisionFire;
import simulation.process.behavior.NextSequenceBasedOnChance;
import simulation.process.behavior.NextSequenceBasedOnCondition;

public class DecisionBasedOnCondition extends Decision{

	private boolean condition;
	private boolean hasBooleanCommandFireAlready;
	private final BooleanCommand boolCommand;
	
	public DecisionBasedOnCondition(String ID, BooleanCommand boolCommand) {
		super(ID);
		nextSequenceBehavior = new NextSequenceBasedOnCondition(this);
		canFireBehavior = new CanFireEntity(this);
		fireBehavior = new DecisionFire(this);
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
