package simulation.process.behavior;

import simulation.entity.Entity;
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
			// Get first entity from queue
			Entity firstEntityFromQueue = currentSeqObj.GetFirstEntityFromQueue();		
			
			currentSeqObj.GetNextSequenceBehavior().SetNextSequenceObjectForEntity(firstEntityFromQueue);
			firstEntityFromQueue.SetFinished();
			currentSeqObj.RemoveFirstEntityFromQueue();
		}
		
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}

	
}
