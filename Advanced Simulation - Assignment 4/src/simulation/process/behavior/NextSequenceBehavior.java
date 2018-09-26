package simulation.process.behavior;

import simulation.entity.Entity;
import simulation.process.SequenceObject;

public abstract class NextSequenceBehavior {

	protected SequenceObject currentSequenceObject;
	
	public NextSequenceBehavior(SequenceObject currentSequenceObject)
	{
		this.currentSequenceObject = currentSequenceObject;
	}
	
	public abstract void SetNextSequenceObjectForEntity(Entity entityForNextSequence);
}
