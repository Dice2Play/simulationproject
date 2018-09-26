package simulation.process.behavior;

import simulation.entity.Entity;
import simulation.process.SequenceObject;

public class NoNextSequence extends NextSequenceBehavior {

	public NoNextSequence(SequenceObject currentSequenceObject) {
		super(currentSequenceObject);
	}

	@Override
	public void SetNextSequenceObjectForEntity(Entity entityForNextSequence) {
		// Do nothing
		
	}

}
