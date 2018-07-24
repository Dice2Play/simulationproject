package simulation.process.behavior;

import simulation.process.SequenceObject;

public class NoNextSequence extends NextSequenceBehavior {

	public NoNextSequence(SequenceObject currentSequenceObject) {
		super(currentSequenceObject);
	}

	@Override
	public void SetNextSequenceObjectForEntity() {
		// Do nothing
		
	}

}
