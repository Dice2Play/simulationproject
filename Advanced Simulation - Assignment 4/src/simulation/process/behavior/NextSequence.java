package simulation.process.behavior;

import simulation.process.SequenceObject;

public class NextSequence {
	
	private SequenceObject nextSequenceObject;
	
	public NextSequence(SequenceObject nextSequenceObject)
	{
		this.nextSequenceObject = nextSequenceObject;
	}
	
	public SequenceObject GetNextSequenceObject()
	{
		return nextSequenceObject;
	}

}
