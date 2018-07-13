package simulation.process.behavior;

import simulation.process.SequenceObject;

public class TerminateFire extends FireBehavior{

	SequenceObject currentSeqObj;
	
	public TerminateFire(SequenceObject seqObj)
	{
		this.currentSeqObj = seqObj;
	}
	
	@Override
	public void Fire() {
		currentSeqObj.GetNextSequenceBehavior().SetNextSequenceObjectForEntity();
		currentSeqObj.RemoveFirstEntityFromQueue();
	}

	
}
