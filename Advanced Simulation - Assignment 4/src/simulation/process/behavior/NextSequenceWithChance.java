package simulation.process.behavior;

import simulation.process.SequenceObject;

public class NextSequenceWithChance extends NextSequence {

	private double chance;
	
	public NextSequenceWithChance(SequenceObject seqObj, double chance)
	{
		super(seqObj);
		this.chance = chance;
		
	}
	
	public double GetChance()
	{
		return chance;
	}
	


	
	
}
