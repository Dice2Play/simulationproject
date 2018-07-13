package simulation.process.behavior;

public class ProcessFire extends FireBehavior{

	private simulation.process.Process process;
	
	public ProcessFire(simulation.process.Process process)
	{
		this.process = process;
	}
	
	@Override
	public void Fire() {
		
		// Seize
		process.Seize();
		
		// Set delayed release
		
		
	}

}
