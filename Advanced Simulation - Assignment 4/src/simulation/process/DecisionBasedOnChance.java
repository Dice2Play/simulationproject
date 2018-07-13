package simulation.process;

import simulation.process.behavior.CanFireEntity;
import simulation.process.behavior.DecisionFire;
import simulation.process.behavior.NextSequenceBasedOnChance;

public class DecisionBasedOnChance extends Decision{

	private final double chanceForFirstSequenceLink;
	private final double chanceForSecondSequenceLink;
	
	public DecisionBasedOnChance(String ID, double chanceForFirstSequenceLink, double chanceForSecondSequenceLink) {
		super(ID);
		nextSequenceBehavior = new NextSequenceBasedOnChance(this);
		canFireBehavior = new CanFireEntity(this);
		fireBehavior = new DecisionFire(this);
		this.chanceForFirstSequenceLink = chanceForFirstSequenceLink;
		this.chanceForSecondSequenceLink = chanceForSecondSequenceLink;
	}
	
	public double GetChanceForFirstSequenceLink()
	{
		return chanceForFirstSequenceLink;
	}
	
	public double GetchanceForSecondSequenceLink()
	{
		return chanceForSecondSequenceLink;
	}
	
	

}
