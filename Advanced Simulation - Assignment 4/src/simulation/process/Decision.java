package simulation.process;

import simulation.entity.Entity;

public class Decision extends SequenceObject{

	public Decision(String ID, Process_Priority processPriority) {
		super(ID, processPriority);
		
	}
	
	public Decision(String ID)
	{
		this(ID, Process_Priority.Normal);
	}

	@Override
	void SetNextSequenceObjectForEntity(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AddNextSequenceLink(SequenceObject seqObject) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean CanFire() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void Fire() {
		// TODO Auto-generated method stub
		
	}

	
	
}
