package simulation.process.behavior;

import simulation.process.SequenceObject;

public class NextSequenceTerminate extends NextSequenceBehavior {

	public NextSequenceTerminate(SequenceObject currentSequenceObject) {
		super(currentSequenceObject);
	}

	@Override
	public void SetNextSequenceObjectForEntity() {
		// Do nothing
		
	}

}
