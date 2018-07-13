package simulation.process;

import simulation.process.behavior.NextSequenceBasedOnChance;

public class DecisionBasedOnChance extends Decision{

	private final double chanceForFirstSequenceLink;
	private final double chanceForSecondSequenceLink;
	
	public DecisionBasedOnChance(String ID, double chanceForFirstSequenceLink, double chanceForSecondSequenceLink) {
		super(ID);
		setNextSequenceBehavior(new NextSequenceBasedOnChance(this));
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
