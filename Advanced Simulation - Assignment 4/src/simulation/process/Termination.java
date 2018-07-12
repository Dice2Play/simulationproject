package simulation.process;

public class Termination extends SequenceObject {

	public Termination(String ID) {
		super(ID, Process_Priority.Normal);
	}

	@Override
	public void SetNextSequenceObjectForEntity() {
		// End of the line, do nothing
	}
	
	
	// End of the line, remove from entity manager and (this)queue
	@Override
	public void Fire() throws Exception
	{
		super.Fire();
		SetNextSequenceObjectForEntity();
		RemoveEntityFromQueue(currentEntity);
		
	}
	
	

	@Override
	public boolean CanFire() {
		return IsThereANextEntityFromQueue();
	}

}
