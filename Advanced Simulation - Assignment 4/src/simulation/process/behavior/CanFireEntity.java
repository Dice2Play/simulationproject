package simulation.process.behavior;

import simulation.process.SequenceObject;

public class CanFireEntity extends CanFireBehavior{

	private SequenceObject sequenceObject;
	
	public CanFireEntity(SequenceObject seqObject)
	{
		this.sequenceObject = seqObject;
	}
	
	@Override
	public boolean CanFire() {
		
		return sequenceObject.IsThereANextEntityFromQueue();
	}

}
