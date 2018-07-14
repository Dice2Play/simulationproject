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
		try
		{
			currentSeqObj.GetNextSequenceBehavior().SetNextSequenceObjectForEntity();
			currentSeqObj.GetNextEntityFromQueue().SetFinished();
			currentSeqObj.RemoveFirstEntityFromQueue();
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	
}
