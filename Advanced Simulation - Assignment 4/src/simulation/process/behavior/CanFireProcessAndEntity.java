package simulation.process.behavior;

public class CanFireProcessAndEntity extends CanFireBehavior{

	private simulation.process.Process process;
	
	public CanFireProcessAndEntity(simulation.process.Process process)
	{
		this.process = process;
	}
	
	@Override
	public boolean CanFire() {
		return process.IsAvailable() && process.IsThereANextEntityFromQueueForCurrentProcess();
	}

}
