package simulation.process.behavior;

public class CanFireResourceAndEntity extends CanFireBehavior{

	private simulation.process.SeizeResource seizeObject;
	
	public CanFireResourceAndEntity(simulation.process.SeizeResource seizeObject)
	{
		this.seizeObject = seizeObject;
	}
	
	@Override
	public boolean CanFire() {
		return seizeObject.AreRequiredResourcesAvailable() && seizeObject.IsThereANextEntityFromQueueForCurrentProcess();
	}

}
