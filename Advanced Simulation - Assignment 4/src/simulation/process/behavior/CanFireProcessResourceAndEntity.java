package simulation.process.behavior;

public class CanFireProcessResourceAndEntity extends CanFireBehavior{

	private simulation.process.Process process;
	
	public CanFireProcessResourceAndEntity(simulation.process.Process process)
	{
		this.process = process;
	}
	
	@Override
	public boolean CanFire() {
		return process.AreResourcesAvailable() && process.IsAvailable() && process.IsThereANextEntityFromQueue();
	}

}
